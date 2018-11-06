/*
 * @CreateTime: Dec 25, 2017 9:41 PM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfreeborn@gmail.com
 * @Last Modified By: undefined
 * @Last Modified Time: Dec 25, 2017 11:55 PM
 * @Description: This code allows the arduino device to store NMEA Sentences 
 *                and accelerometer values in m/s^2 to a micro SD card where
 *                the file creates under the name GPSData.TXT can be opened
 *                with the NMEAtoEXCEL program to convert the raw data to 
 *                strokes/min, time, speed, and distance. 
 * @Pin Layout: 
 *        SD breakout: 
 * 
 */

#include <SD.h> //Load SD card library
#include<SPI.h> //Load SPI Library
#include <Adafruit_GPS.h>    //Install the adafruit GPS library
#include <SoftwareSerial.h> //Load the Software Serial library

//pin used for led
#define ledpin 8

// x-axis of the accelerometer
#define x A0
// x-axis of the accelerometer
#define y  A1
// x-axis of the accelerometer
#define z  A2 

#define FIXpin  7 

//chipSelect pin for the SD card Reader
#define chipSelect  4 

SoftwareSerial mySerial(3,2); //Initialize the Software Serial port
Adafruit_GPS GPS(&mySerial); //Create the GPS Object
 
String NMEA1; //Variable for first NMEA sentence
String NMEA2; //Variable for second NMEA sentence
char c; //to read characters coming from the GPS


int xinit; // initial x value used for calibration
int yinit; // initial y value used for claibration
float zinit; // initial z value used for claibration

char an0[4], an1[4], an2[4];  // char variables to store analog pin values. Total 6 pins from 0-5

File GPSlog; //Data object you will write your sesnor data to
 



 
void setup() {
  Serial.begin(115200); //Turn on serial monitor
  GPS.begin(9600); //Turn on GPS at 9600 baud
  //GPS.sendCommand("$PGCMD,33,0*6D");  //Turn off antenna update nuisance data
  GPS.sendCommand(PMTK_SET_NMEA_OUTPUT_RMCONLY); //Request RMC and GGA Sentences only
  GPS.sendCommand(PMTK_SET_NMEA_UPDATE_10HZ); //Set update rate to 1 hz
  delay(1000); 

  CalibrateAccel();
  
  pinMode(10, OUTPUT); //Must declare 10 an output and reserve it to keep SD card happy
 
  DDRB |=(1<<DDB0); // led pin set to output


  pinMode(FIXpin, INPUT);
  unsigned int firstBlink = millis();
  unsigned int SecoundBlink = millis();
  while((millis()-firstBlink - 2) < millis()-SecoundBlink)
  {
   while(!digitalRead(FIXpin, ))
   {}
   unsigned int firstBlink = millis();
   while(!digitalRead(FIXpin))
   {}
   unsigned int SecoundBlink = millis();
  }
  PORTB |=(1<<PORTB0); //led pin high


  SD.begin(chipSelect); //Initialize the SD card reader
  
  if (SD.exists("NMEA.txt")) { //Delete old data files to start fresh
    SD.remove("NMEA.txt");
    while()
  }
}
 
void loop() {
  
  readGPS();
  
 // if(GPS.fix==1) { //Only save data if we have a fix
  PORTB |=(1<<PORTB0); //led pin high
  GPSlog = SD.open("GPSData.txt", FILE_WRITE); //Open file on SD card for writing
  
  GPSlog.println(GPS.hour, DEC); //Write first NMEA to SD card
  GPSlog.println(GPS.minute, DEC); //Write Second NMEA to SD card
  GPSlog.println(GPS.seconds, DEC); //Write Second NMEA to SD card
  
  itoa (((analogRead(x)-xinit)/671.3),  an0, 10);    // X read and convert numeric analog pin to char
  itoa (((analogRead(y)-yinit)/671.3),  an1, 10);    // Y  ..
    
  //switch for float 
  itoa ((((analogRead(z)-zinit)/671.3)+9.8),  an2, 10);    // Z converted to char (the reacion for the +9.8 is to compensate for gravity)

  if (GPSlog)
  {
    GPSlog.print(an0);   GPSlog.print(" , "); // write ANALOG0 (X) to SDGPSlog.print(" , ");      
    GPSlog.print(an1);   GPSlog.print(" , "); // write ANALOG1 (Y) to SD     
    GPSlog.println(an2);    // write ANALOG2 (Z) to SD
  }
     else {
      // if the file didn't openf
    Serial.println("wrighting failed!");// replace with led
    }
  PORTB &=~(1<<PORTB0); //PIN LOW
  GPSlog.close();
 // }
  
}

void readGPS() {
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
  
  Serial.println(GPS.hour, DEC);
  Serial.println(GPS.minute, DEC);
  Serial.println(GPS.seconds, DEC);
  Serial.println("");
  
}
void CalibrateAccel()
{
  Serial.println("lay the unit flat on the ground"); //print x value on serial monitor
  delay(1000);
  Serial.println("claibrating"); //print x value on serial monitor
  xinit = analogRead(x);
  yinit = analogRead(y);
  zinit = analogRead(z);
  Serial.println("claibration done"); //print x value on serial monitor
}