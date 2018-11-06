
#include <SPI.h>
#include <SD.h>
//#define LED 8           // status LED for SD operations
//#define BUFF_MAX 100   // size of GPS & SD buffers

File GPSlog;

void SDsetup();
void SDopen();
void AccelReadtoSD();
void SDclose();
void CalibrateAccel();

const int xpin = A0; // x-axis of the accelerometer
const int ypin = A1; // y-axis
const int zpin = A2; // z-axis (only on 3-axis models)

int xinit;
int yinit;
float zinit;
void setup() {

 Serial.begin(9600);
  while (!Serial) {
    ; // wait for serial port to connect. Needed for native USB port only
  }
 SDsetup();
 CalibrateAccel();
}
void loop() {
  SDopen();
  AccelReadtoSD();
  SDclose();
  float zero_G = 512.0; //ADC is 0~1023 the zero g output equal to Vs/2
  //ADXL335 power supply by Vs 3.3V
  float scale = 102.3; //ADXL335330 Sensitivity is 330mv/g
  delay(10000); //wait for 1 second 

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
  GPSlog = SD.open("GPS.log", O_CREAT | O_WRITE);  // open/append to a file GPS.log
  if (!GPSlog) {               // test if file can be opened
     Serial.println("opening failed!");// replace with led
    return;
  }
  else  Serial.println("opening done!");// replace with led
}
void SDclose()
{
    GPSlog.close();
}
void SDsetup()
{
  Serial.print("Initializing SD card...");// replace with led

  if (!SD.begin(4)) {
    Serial.println("initialization failed!");// replace with led
    return;
  }
  Serial.println("initialization done.");// replace with led
}
void CalibrateAccel()
{
  Serial.print("lay the unit flat on the ground"); //print x value on serial monitor
  delay(1000);
  Serial.print("claibrating"); //print x value on serial monitor
  xinit = analogRead(xpin);
  yinit = analogRead(ypin);
  zinit = (analogRead(zpin)-(9.8*68));
  Serial.print("claibration done"); //print x value on serial monitor
}