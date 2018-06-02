#define BLUE_PIN 7
#define WHITE_PIN 8
#define YELLOW_PIN 9
#define RED_PIN 10
#define BLACK_PIN 11
#define BROWN_PIN 12

#define DELAY_TIME 700

void reset_pins()
{
  pinMode(BLUE_PIN, INPUT);
  pinMode(WHITE_PIN, INPUT);
  pinMode(YELLOW_PIN, INPUT);
  pinMode(RED_PIN, INPUT);
  pinMode(BLACK_PIN, INPUT);
  pinMode(BROWN_PIN, INPUT);
  digitalWrite(BLUE_PIN, LOW);
  digitalWrite(WHITE_PIN, LOW);
  digitalWrite(YELLOW_PIN, LOW);
  digitalWrite(RED_PIN, LOW);
  digitalWrite(BLACK_PIN, LOW);
  digitalWrite(BROWN_PIN, LOW);
  delay(100);
}
void set_pins(int high_pin, int low_pin)
{
  reset_pins();

  // set the high and low pins to output
  pinMode(high_pin, OUTPUT);
  pinMode(low_pin, OUTPUT);

  // set logic as required
  digitalWrite(high_pin, HIGH);
  digitalWrite(low_pin, LOW);
}

void turn_on_letter_A(){set_pins(BLUE_PIN,WHITE_PIN);}
void turn_on_letter_B(){set_pins(WHITE_PIN,BLUE_PIN);}
void turn_on_letter_C(){set_pins(WHITE_PIN,YELLOW_PIN);}
void turn_on_letter_D(){set_pins(YELLOW_PIN,WHITE_PIN);}
void turn_on_letter_E(){set_pins(YELLOW_PIN,RED_PIN);}
void turn_on_letter_F(){set_pins(RED_PIN,YELLOW_PIN);}
void turn_on_letter_G(){set_pins(BLACK_PIN,RED_PIN);}
void turn_on_letter_H(){set_pins(RED_PIN,BLACK_PIN);}
void turn_on_letter_I(){set_pins(BROWN_PIN,BLACK_PIN);}
void turn_on_letter_J(){set_pins(BLACK_PIN,BROWN_PIN);}
void turn_on_letter_K(){set_pins(BLUE_PIN,YELLOW_PIN);}
void turn_on_letter_L(){set_pins(YELLOW_PIN,BLUE_PIN);}
void turn_on_letter_M(){set_pins(WHITE_PIN,RED_PIN);}
void turn_on_letter_N(){set_pins(RED_PIN,WHITE_PIN);}
void turn_on_letter_O(){set_pins(BLACK_PIN,YELLOW_PIN);}
void turn_on_letter_P(){set_pins(YELLOW_PIN,BLACK_PIN);}
void turn_on_letter_Q(){set_pins(BROWN_PIN,RED_PIN);}
void turn_on_letter_Z(){set_pins(RED_PIN,BROWN_PIN);}
void turn_on_letter_R(){set_pins(BLACK_PIN,BLUE_PIN);}
void turn_on_letter_S(){set_pins(BLUE_PIN,BLACK_PIN);}
void turn_on_letter_T(){set_pins(YELLOW_PIN,BROWN_PIN);}
void turn_on_letter_U(){set_pins(BROWN_PIN,YELLOW_PIN);}
void turn_on_letter_V(){set_pins(BLACK_PIN,WHITE_PIN);}
void turn_on_letter_W(){set_pins(WHITE_PIN,BLACK_PIN);}
void turn_on_letter_X(){set_pins(BLUE_PIN,RED_PIN);}
void turn_on_letter_Y(){set_pins(RED_PIN,BLUE_PIN);}

void print_char(char ch){
  switch(ch){
    case 'a': turn_on_letter_A();
      break;
    case 'b': turn_on_letter_B();
      break;
    case 'c': turn_on_letter_C();
      break;
    case 'd': turn_on_letter_D();
      break;
    case 'e': turn_on_letter_E();
      break;
    case 'f': turn_on_letter_F();
      break;
    case 'g': turn_on_letter_G();
      break;
    case 'h': turn_on_letter_H();
      break;
    case 'i': turn_on_letter_I();
      break;
    case 'j': turn_on_letter_J();
      break;
    case 'k': turn_on_letter_K();
      break;
    case 'l': turn_on_letter_L();
      break;
    case 'm': turn_on_letter_M();
      break;
    case 'n': turn_on_letter_N();
      break;
    case 'o': turn_on_letter_O();
      break;
    case 'p': turn_on_letter_P();
      break;
    case 'q': turn_on_letter_Q();
      break;
    case 'r': turn_on_letter_R();
      break;
    case 's': turn_on_letter_S();
      break;
    case 't': turn_on_letter_T();
      break;
    case 'u': turn_on_letter_U();
      break;
    case 'v': turn_on_letter_V();
      break;
    case 'w': turn_on_letter_W();
      break;
    case 'x': turn_on_letter_X();
      break;
    case 'y': turn_on_letter_Y();
      break;
    case 'z': turn_on_letter_Z();
      break;
  }
}
void print_word(String word){
  word.toLowerCase();
  
  for(int i =0; i< word.length(); i++){  
    print_char(word[i]);      
    delay(DELAY_TIME);
  }
  reset_pins();
}
void setup()
{
  reset_pins();
  Serial.begin(9600);
}
void loop()
{
  
  if(Serial.available() > 0){
    String word = "";
    while(Serial.available() > 0){
     char ch = Serial.read();
     //Serial.write(ch);
      word+= ch;
    }
    print_word(word);
  }  
}
