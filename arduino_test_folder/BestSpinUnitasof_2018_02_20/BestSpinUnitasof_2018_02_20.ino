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

// x-axis of the accelerometer
#define xpin A0
// y-axis of the accelerometer
#define ypin  A1
// z-axis of the accelerometer
#define zpin  A2 

//chipSelect pin for the SD card Reader
#define chipSelect  4 
// If using software serial, keep this line enabled
// (you can change the pin numbers to match your wiring):
SoftwareSerial mySerial(3, 2);

// If using hardware serial (e.g. Arduino Mega), comment out the
// above SoftwareSerial line, and enable this line instead
// (you can change the Serial number to match your wiring):

Adafruit_GPS GPS(&mySerial);

// Set GPSECHO to 'false' to turn off echoing the GPS data to the Serial console
// Set to 'true' if you want to debug and listen to the raw GPS sentences. 
#define GPSECHO  false

// this keeps track of whether we're using the interrupt
// off by default!
boolean usingInterrupt = true;
void useInterrupt(boolean); // Func prototype keeps Arduino 0023 happy

//if true the program will also read accelerometer values.
#define Accelerometer  true

//if true the program will also wright values to the SDCard.
#define SDCard  true

File GPSlog; //Data object you will write your sesnor data to

void setup()  
{
  // connect at 115200 so we can read the GPS fast enough and echo without dropping chars
  // also spit it out
  Serial.begin(115200);
  Serial.println(F("Adafruit GPS library basic test!"));

  // 9600 NMEA is the default baud rate for Adafruit MTK GPS's- some use 4800
  GPS.begin(9600);

  // uncomment this line to turn on RMC (recommended minimum) and GGA (fix data) including altitude
  GPS.sendCommand(PMTK_SET_NMEA_OUTPUT_RMCGGA);

  // uncomment this line to turn on only the "minimum recommended" data
  //GPS.sendCommand(PMTK_SET_NMEA_OUTPUT_RMCONLY);
  // For parsing data, we don't suggest using anything but either RMC only or RMC+GGA since
  // the parser doesn't care about other sentences at this time

  // Set the update rate
  GPS.sendCommand(PMTK_SET_NMEA_UPDATE_1HZ);   // 1 Hz update rate

  // For the parsing code to work nicely and have time to sort thru the data, and
  // print it out we don't suggest using anything higher than 1 Hz

  // Request updates on antenna status, comment out to keep quiet
  GPS.sendCommand(PGCMD_ANTENNA);

  // the nice thing about this code is you can have a timer0 interrupt go off
  // every 1 millisecond, and read data from the GPS for you. that makes the
  // loop code a heck of a lot easier!
  useInterrupt(true);

  delay(1000);
  // Ask for firmware version
  mySerial.println(PMTK_Q_RELEASE);

  if(SDCard)
  {
    pinMode(10, OUTPUT); //Must declare 10 an output and reserve it to keep SD card happy
    DDRB |=(1<<DDB0); // led pin set to output
    SD.begin(chipSelect); //Initialize the SD card reader
  }

}

// Interrupt is called once a millisecond, looks for any new GPS data, and stores it
SIGNAL(TIMER0_COMPA_vect) {
  char c = GPS.read();
  // if you want to debug, this is a good time to do it!
#ifdef UDR0
  if (GPSECHO)
    if (c) UDR0 = c;  
    // writing direct to UDR0 is much much faster than Serial.print 
    // but only one character can be written at a time. 
#endif
}



void useInterrupt(boolean v) {
  if (v) {
    // Timer0 is already used for millis() - we'll just interrupt somewhere
    // in the middle and call the "Compare A" function above
    OCR0A = 0xAF;
    TIMSK0 |= _BV(OCIE0A);
    usingInterrupt = true;
  } else {
    // do not call the interrupt function COMPA anymore
    TIMSK0 &= ~_BV(OCIE0A);
    usingInterrupt = false;
  }
}

void AccelerometerWright()
{
  PORTB |=(1<<PORTB0); //led pin high
  GPSlog = SD.open("GPSData.txt", FILE_WRITE); //Open file on SD card for writing
  //---------------File opened -------------------//
  PORTB |=(1<<PORTB0); //led pin high
  if (GPSlog)
  {
    GPSlog.print(analogRead(xpin));   GPSlog.print(" , "); // write ANALOG0 (X) to SDGPSlog.print(" , ");      
    GPSlog.print(analogRead(ypin));   GPSlog.print(" , "); // write ANALOG1 (Y) to SD     
    GPSlog.println(analogRead(zpin));    // write ANALOG2 (Z) to SD
    //add the calabrated values based on the adafruit accelerometer calabration code
          
          
    Serial.print(((float)analogRead(xpin) - 331.5)/65*9.8);   Serial.print(" , "); // write ANALOG0 (X) to SDGPSlog.print(" , ");      
    Serial.print(((float)analogRead(ypin) - 329.5)/68.5*9.8);   Serial.print(" , "); // write ANALOG1 (Y) to SD     
    Serial.println(((float)analogRead(zpin) - 340)/68*9.8);    // write ANALOG2 (Z) to SD
  }
  else 
  { // if the file didn't open
  //if no longer connected to the serial monitor simply comment out the following line
  Serial.println(F("wrighting Accelerometer Data failed!"));// replace with led
  }
  GPSlog.close();
  //---------------File closed -------------------//
  PORTB &=~(1<<PORTB0); //PIN LOW
  GPSlog.close();
  //---------------File closed -------------------//
  PORTB &=~(1<<PORTB0); //PIN LOW
}

uint32_t timer = millis();
void loop()                     // run over and over again
{
  // in case you are not using the interrupt above, you'll
  // need to 'hand query' the GPS, not suggested :(
  if (! usingInterrupt) {
    // read data from the GPS in the 'main loop'
    char c = GPS.read();
    // if you want to debug, this is a good time to do it!
    if (GPSECHO)
      if (c) Serial.print(c);
  }

  // if a sentence is received, we can check the checksum, parse it...
  if (GPS.newNMEAreceived()) {
    // a tricky thing here is if we print the NMEA sentence, or data
    // we end up not listening and catching other sentences! 
    // so be very wary if using OUTPUT_ALLDATA and trytng to print out data
    //Serial.println(GPS.lastNMEA());   // this also sets the newNMEAreceived() flag to false

    if (!GPS.parse(GPS.lastNMEA()))   // this also sets the newNMEAreceived() flag to false
      return;  // we can fail to parse a sentence in which case we should just wait for another
  }

  // if millis() or timer wraps around, we'll just reset it
  if (timer > millis())  timer = millis();

  /*
  Accelerometer wrights to SD Card
  */
  if(Accelerometer)
  {
      if(SDCard)
      {   
        //-------------Changed Code start------------------//
          AccelerometerWright();
        //-------------Changed Code end------------------//
        //----------------old code start----------------//
        /*
        PORTB |=(1<<PORTB0); //led pin high
        GPSlog = SD.open("GPSData.txt", FILE_WRITE); //Open file on SD card for writing
        //---------------File opened -------------------//
        PORTB |=(1<<PORTB0); //led pin high
        if (GPSlog)
        {
          GPSlog.print(analogRead(xpin));   GPSlog.print(" , "); // write ANALOG0 (X) to SDGPSlog.print(" , ");      
          GPSlog.print(analogRead(ypin));   GPSlog.print(" , "); // write ANALOG1 (Y) to SD     
          GPSlog.println(analogRead(zpin));    // write ANALOG2 (Z) to SD

          
          //add the calabrated values based on the adafruit accelerometer calabration code
          
          
          Serial.print(((float)analogRead(xpin) - 331.5)/65*9.8);   Serial.print(" , "); // write ANALOG0 (X) to SDGPSlog.print(" , ");      
          Serial.print(((float)analogRead(ypin) - 329.5)/68.5*9.8);   Serial.print(" , "); // write ANALOG1 (Y) to SD     
          Serial.println(((float)analogRead(zpin) - 340)/68*9.8);    // write ANALOG2 (Z) to SD
          
          
          
        }
        else 
        { // if the file didn't open
          //if no longer connected to the serial monitor simply comment out the following line
          Serial.println("wrighting Accelerometer Data failed!");// replace with led
        }
        GPSlog.close();
        //---------------File closed -------------------//
        PORTB &=~(1<<PORTB0); //PIN LOW
        */
        //----------------old code end----------------//
      }
  }
    /* 
    after approximatly one secound has passed collect the GPS values
    */
  if (millis() - timer > 1000) { 
    timer = millis(); // reset the timer

    Serial.print(F("\nTime: "));
    Serial.print(GPS.hour, DEC); Serial.print(F(':'));
    Serial.print(GPS.minute, DEC); Serial.print(F(':'));
    Serial.print(GPS.seconds, DEC); Serial.print(F('.'));
    Serial.println(GPS.milliseconds);
    Serial.print(F("Date: "));
    Serial.print(GPS.day, DEC); Serial.print(F('/'));
    Serial.print(GPS.month, DEC); Serial.print(F("/20"));
    Serial.println(GPS.year, DEC);
    Serial.println(GPS.speed, DEC);
    Serial.print(F("Fix: ")); Serial.println((short)GPS.fix);
   // Serial.print(" quality: "); Serial.println((int)GPS.fixquality); 
    if (GPS.fix) 
    {
      if(SDCard)
      {   
        PORTB |=(1<<PORTB0); //led pin high
        
        GPSlog = SD.open("GPSData.txt", FILE_WRITE); //Open file on SD card for writing
        //---------------File Opened -------------------//

        if (GPSlog)
        { 
          GPSlog.print("\nTime: ");
          GPSlog.print(GPS.hour, DEC); GPSlog.print(':');
          GPSlog.print(GPS.minute, DEC); GPSlog.print(':');
          GPSlog.print(GPS.seconds, DEC); GPSlog.print('.');
          GPSlog.println(GPS.milliseconds);
          GPSlog.print("Date: ");
          GPSlog.print(GPS.day, DEC); GPSlog.print('/');
          GPSlog.print(GPS.month, DEC); GPSlog.print("/20");
          GPSlog.println(GPS.year, DEC);
          GPSlog.print("Location (in degrees, works with Google Maps): ");
          GPSlog.print(GPS.latitudeDegrees, 4);
          GPSlog.print(", "); 
          GPSlog.println(GPS.longitudeDegrees, 4);
          GPSlog.print("Speed (m): "); GPSlog.println(GPS.speed);
        }
        else 
        { // if the file didn't openf
          //if no longer connected to the serial monitor simply comment out the following line
          Serial.println(F("wrighting GPS Data failed!"));// replace with led
        }
        PORTB &=~(1<<PORTB0); //PIN LOW
        //---------------File closed -------------------//
        GPSlog.close();
      }
    }
    
  }
}
