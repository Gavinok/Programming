
#include <SPI.h>
#include <SD.h>
#include <Adafruit_GPS.h>    //Install the adafruit GPS library
#include <SoftwareSerial.h> //Load the Software Serial library

#define ChipSelect  4
#define ledpin 8

SoftwareSerial mySerial(3,2); //Initialize the Software Serial port
Adafruit_GPS GPS(&mySerial); //Create the GPS Object



File GPSlog;

void SDsetup();
void SDopen();
void AccelReadtoSD();
void SDclose();
void CalibrateAccel();

const int xpin = A0; // x-axis of the accelerometer
const int ypin = A1; // y-axis
const int zpin = A2; // z-axis (only on 3-axis models)

String NMEA1; //Variable for first NMEA sentence
String NMEA2; //Variable for second NMEA sentence
char c; //to read characters coming from the GPS

int xinit;
int yinit;
float zinit;
void setup() {

 Serial.begin(115200);
 pinMode(ledpin, OUTPUT);
  while (!Serial) {
    ; // wait for serial port to connect. Needed for native USB port only
  }
 SDsetup();
 CalibrateAccel();
 GPSsetup();
 SDopen();
}
void loop() {
  readGPS();
  if(GPS.fix==1) { //Only save data if we have a fix
    // SDopen();
    GPSReadtoSD();
    // SDclose();
  //}
  //if (GPSlog) {
  //SDopen();
  AccelReadtoSD();
  SDclose();
  }
  float zero_G = 512.0; //ADC is 0~1023 the zero g output equal to Vs/2
  //ADXL335 power supply by Vs 3.3V
  float scale = 102.3; //ADXL335330 Sensitivity is 330mv/g
  delay(10000); //wait for 1 second 

}
void GPSsetup(){
  Serial.print("Initializing GPS...");// replace with led
  GPS.begin(9600); //Turn on GPS at 9600 baud
  GPS.sendCommand("$PGCMD,33,0*6D");  //Turn off antenna update nuisance data
  GPS.sendCommand(PMTK_SET_NMEA_OUTPUT_RMCGGA); //Request RMC and GGA Sentences only
  GPS.sendCommand(PMTK_SET_NMEA_UPDATE_1HZ); //Set update rate to 1 hz
  delay(1000); 
  Serial.println("initialization done.");// replace with led    
}
void GPSReadtoSD()
{
 // digitalWrite(ledpin, HIGH);
  GPSlog.print(GPS.latitude,4); //Write measured latitude to file
  GPSlog.print(GPS.lat); //Which hemisphere N or S
  GPSlog.print(",");
  GPSlog.print(GPS.longitude,4); //Write measured longitude to file
  GPSlog.print(GPS.lon); //Which Hemisphere E or W
  GPSlog.print(",");
  GPSlog.println(GPS.altitude);
  //digitalWrite(ledpin, LOW);
}
void AccelReadtoSD()
{
    char an0[4], an1[4], an2[4];  // char variables to store analog pin values. Total 6 pins from 0-5
    itoa (((analogRead(xpin)-xinit)/68.5*9.8),  an0, 10);    // X read and convert numeric analog pin to char
    itoa (((analogRead(ypin)-yinit)/68.5*9.8),  an1, 10);    // Y  ..
    itoa (((analogRead(zpin)-zinit)/68.5*9.8),  an2, 10);    // Z  ..
    if (GPSlog) {
      Serial.println("wrighting");// replace with led
      GPSlog.print(an0);    // write ANALOG0 (X) to SD
      GPSlog.print(" , ");      
      GPSlog.print(an1);    // write ANALOG1 (Y) to SD     
      GPSlog.print(" , ");      
      GPSlog.print(an2);    // write ANALOG2 (Z) to SD
      GPSlog.print(" , ");     
      Serial.println("wrighting done!");// replace with led
    }
     else {
      // if the file didn't open, turn LED off
     Serial.println("wrighting failed!");// replace with led
    }
}
void SDopen()
{
  GPSlog = SD.open("GPS.txt", O_CREAT | O_WRITE);  // open/append to a file GPS.log
  if (!GPSlog) {               // test if file can be opened
     Serial.println("opening failed!");// replace with led
    return;
  }
  else  Serial.println("opening done!");// replace with led
}
void SDclose()
{
    GPSlog.close();
    digitalWrite(ledpin, LOW);
    
}
void SDsetup()
{
  Serial.print("Initializing SD card...");// replace with led

  if (!SD.begin(ChipSelect)) {
    Serial.println("initialization failed!");// replace with led
    return;
  }
 
  Serial.println("initialization done.");// replace with led
}
void CalibrateAccel()
{
  Serial.println("lay the unit flat on the ground"); //print x value on serial monitor
  delay(1000);
  Serial.println("claibrating"); //print x value on serial monitor
  xinit = analogRead(xpin);
  yinit = analogRead(ypin);
  zinit = (analogRead(zpin)-(9.8*68));
  Serial.println("claibration done"); //print x value on serial monitor
}
void readGPS() {
  
  clearGPS();
  while(!GPS.newNMEAreceived()) { //Loop until you have a good NMEA sentence
    c=GPS.read();
  }
  GPS.parse(GPS.lastNMEA()); //Parse that last good NMEA sentence
  NMEA1=GPS.lastNMEA();
  
   while(!GPS.newNMEAreceived()) { //Loop until you have a good NMEA sentence
    c=GPS.read();
  }
  GPS.parse(GPS.lastNMEA()); //Parse that last good NMEA sentence
  NMEA2=GPS.lastNMEA();
   digitalWrite(ledpin, HIGH);
  Serial.println(NMEA1);
  Serial.println(NMEA2);
  Serial.println("");
  
}
void clearGPS() {  //Clear old and corrupt data from serial port 
  while(!GPS.newNMEAreceived()) { //Loop until you have a good NMEA sentence
    c=GPS.read();
  }
  GPS.parse(GPS.lastNMEA()); //Parse that last good NMEA sentence
  
  while(!GPS.newNMEAreceived()) { //Loop until you have a good NMEA sentence
    c=GPS.read();
  }
  GPS.parse(GPS.lastNMEA()); //Parse that last good NMEA sentence
   while(!GPS.newNMEAreceived()) { //Loop until you have a good NMEA sentence
    c=GPS.read();
  }
  GPS.parse(GPS.lastNMEA()); //Parse that last good NMEA sentence
  
}