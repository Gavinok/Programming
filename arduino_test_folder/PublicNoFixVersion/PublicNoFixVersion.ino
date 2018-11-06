/*
 * @CreateTime: Dec 25, 2017 9:41 PM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfreeborn@gmail.com
 * @Last Modified By:  Gavin Jaeger-Freeborn
 * @Last Modified Time: Dec 25, 2017 11:55 PM
 * @Description:  This code requires the instalationm of the Adafruit_GPS.h library for it to work
 *                This code allows the arduino device to store the time, and speed of the GPS unit
 *                and accelerometer values in m/s^2 to a micro SD card where the file creates under
 *                the name GPSData.TXT.
 * @Pin Layout: 
 *        GPS Breakout: VIN to +5V
 *                      GND to Ground
 *                      RX to digital 2
 *                      TX to digital 3
 * 
 *    SD Card Breakout: MOSI to digital 11
 *                      MISO to digital 12
 *                      SCK (CLK) to digital 13
 *                      CS to digital 4
 *       Accelerometer: GND to Ground
 *                      X_OUT to analog 0
 *                      Y_OUT to analog 1
 *                      Z_OUT to analog 2
 *                      VCC to +5V
 *                 LED: pin 8
 *              
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
  //if no longer connected to the serial monitor simply comment out the following line
  Serial.begin(115200); //Turn on serial monitor

  GPS.begin(9600); //Turn on GPS at 9600 baud
  GPS.sendCommand(PMTK_SET_NMEA_OUTPUT_RMCONLY); //Request RMC and GGA Sentences only
  GPS.sendCommand(PMTK_SET_NMEA_UPDATE_10HZ); //Set update rate to 10 hz
  delay(1000); 

  CalibrateAccel();
  
  pinMode(10, OUTPUT); //Must declare 10 an output and reserve it to keep SD card happy
 
  DDRB |=(1<<DDB0); // led pin set to output

  SD.begin(chipSelect); //Initialize the SD card reader
}
 
void loop() {
  
  StoreNMEA();
  
 // if(GPS.fix==1) { //Only save data if we have a fix
  PORTB |=(1<<PORTB0); //led pin high
  GPSlog = SD.open("GPSData.txt", FILE_WRITE); //Open file on SD card for writing
  
  GPSlog.println(GPS.hour, DEC); //Write first NMEA to SD card
  GPSlog.println(GPS.minute, DEC); //Write Second NMEA to SD card
  GPSlog.println(GPS.seconds, DEC); //Write Second NMEA to SD card
  GPSlog.println(GPS.speed, DEC); //Write Second NMEA to SD card
  
  itoa (((analogRead(x)-xinit)/671.3),  an0, 10);    // X read and convert numeric analog pin to char
  itoa (((analogRead(y)-yinit)/671.3),  an1, 10);    // Y  ..
    

  itoa ((((analogRead(z)-zinit)/671.3)+9.8),  an2, 10);    // Z converted to char (the reacion for the +9.8 is to compensate for gravity)

  if (GPSlog)
  {
    GPSlog.print(an0);   GPSlog.print(" , "); // write ANALOG0 (X) to SDGPSlog.print(" , ");      
    GPSlog.print(an1);   GPSlog.print(" , "); // write ANALOG1 (Y) to SD     
    GPSlog.println(an2);    // write ANALOG2 (Z) to SD
    Serial.print(an0);   Serial.print(" , "); // write ANALOG0 (X) to SDGPSlog.print(" , ");      
    Serial.print(an1);   Serial.print(" , "); // write ANALOG1 (Y) to SD     
    Serial.println(an2);    // write ANALOG2 (Z) to SD
  }
     else {// if the file didn't openf
     //if no longer connected to the serial monitor simply comment out the following line
    Serial.println("wrighting failed!");// replace with led
    }
  PORTB &=~(1<<PORTB0); //PIN LOW
  GPSlog.close();
  
}


/* 
 * StoreNMEA() simply reads takes in two NMEA sentances and parses them so that the program can produce reading such as speed, time, and location.
 * This function also stores these two NMEA sentances in the variables NMEA1 and NMEA1 incase raw NMEA are desired. A time stamp can then be printed 
 * to the serial monitor for debugging if needed
 */
void StoreNMEA() {
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
  
  //if no longer connected to the serial monitor simply comment out the following four lines
  Serial.println(GPS.hour, DEC);
  Serial.println(GPS.minute, DEC);
  Serial.println(GPS.seconds, DEC);
  Serial.println("");
  
}
void CalibrateAccel()
{
  //if no longer connected to the serial monitor simply comment out the following line
  Serial.println("lay the unit flat on the ground"); //print x value on serial monitor
  
  delay(1000);

  //if no longer connected to the serial monitor simply comment out the following line
  Serial.println("claibrating"); //print x value on serial monitor
 
  xinit = analogRead(x);
  yinit = analogRead(y);
  zinit = analogRead(z);
  //if no longer connected to the serial monitor simply comment out the following line
  Serial.println("claibration done"); //print x value on serial monitor
}