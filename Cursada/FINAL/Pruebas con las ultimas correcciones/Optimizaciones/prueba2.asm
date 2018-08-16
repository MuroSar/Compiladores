.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
__CERO DQ 0.0
__MIN_DOUBLE DQ 2.2250738585072014E-308
__MAX_DOUBLE DQ 1.7976931348623157E308
_msjDC DB "Error: Division por cero", 0
_msjOverflow DB "Error: Overflow", 0
var@@aux59 DD ?
salida1@Funcion@main DD ?
var@@aux19 DD ?
var@@aux18 DD ?
a@Variable@main DD ?
var@@aux9 DD ?
var@@aux6 DD ?
var@@aux5 DD ?
var@@aux60 DD ?
var@@aux7 DD ?
var@@aux62 DD ?
var@@aux4 DD ?
var@@aux63 DD ?
var@@aux22 DD ?
var@@aux21 DD ?
var@@aux24 DD ?
var@@aux48 DD ?
var@@aux49 DD ?
c@Variable@main DD ?
var@@aux52 DD ?
var@@aux11 DD ?
ab1@Variable@main@salida1 DD ?
var@@aux36 DD ?
var@@aux38 DD ?
d@Variable@main DD ?
var@@aux46 DD ?
var@@aux45 DD ?
var@@aux25 DD ?
b@Variable@main DD ?
var@@aux33 DD ?
var@@aux32 DD ?
var@@aux35 DD ?
cadena@@314 DB "Optimiza 1", 0
cadena@@315 DB "Error 1", 0
cadena@@316 DB "Optimiza 2", 0
cadena@@317 DB "Error 2", 0
cadena@@318 DB "Optimiza 3", 0
cadena@@319 DB "Error 3", 0
cadena@@320 DB "Optimiza 4", 0
cadena@@321 DB "Error 4", 0
cadena@@322 DB "Nunca llega if", 0
cadena@@323 DB "nunca llega else", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida1 proc
MOV ab1@Variable@main@salida1,1
MOV EAX,ab1@Variable@main@salida1
MOV salida1@Funcion@main,EAX
RET
salida1 endp
start:
MOV a@Variable@main,10
MOV b@Variable@main,10
MOV c@Variable@main,10
Label3:
MOV EAX,b@Variable@main
MOV EDX,c@Variable@main
MUL EDX
MOV var@@aux5,EAX
MOV EAX,a@Variable@main
ADD EAX,var@@aux5
JO _overflow
MOV var@@aux6,EAX
MOV EAX,var@@aux6
ADD EAX,a@Variable@main
JO _overflow
MOV var@@aux7,EAX
MOV EAX,var@@aux7
ADD EAX,var@@aux5
JO _overflow
MOV var@@aux9,EAX
CALL salida1
MOV EAX,var@@aux9
SUB EAX,salida1@Funcion@main
MOV var@@aux11,EAX
MOV EAX,var@@aux11
MOV d@Variable@main,EAX
MOV EAX,d@Variable@main
MOV EDX,219
CMP d@Variable@main,EDX
JNE Label17
invoke MessageBox, NULL, addr cadena@@314, addr cadena@@314, MB_OK
JMP Label18
Label17:
invoke MessageBox, NULL, addr cadena@@315, addr cadena@@315, MB_OK
Label18:
MOV EAX,b@Variable@main
MOV EDX,c@Variable@main
MUL EDX
MOV var@@aux18,EAX
MOV EAX,a@Variable@main
SUB EAX,var@@aux18
MOV var@@aux19,EAX
CALL salida1
MOV EAX,var@@aux19
ADD EAX,salida1@Funcion@main
JO _overflow
MOV var@@aux21,EAX
MOV EAX,var@@aux21
ADD EAX,a@Variable@main
JO _overflow
MOV var@@aux22,EAX
MOV EAX,var@@aux22
SUB EAX,var@@aux18
MOV var@@aux24,EAX
MOV EAX,var@@aux24
ADD EAX,a@Variable@main
JO _overflow
MOV var@@aux25,EAX
MOV EAX,var@@aux25
MOV d@Variable@main,EAX
MOV EAX,d@Variable@main
MOV EDX,-169
CMP d@Variable@main,EDX
JNE Label31
invoke MessageBox, NULL, addr cadena@@316, addr cadena@@316, MB_OK
JMP Label32
Label31:
invoke MessageBox, NULL, addr cadena@@317, addr cadena@@317, MB_OK
Label32:
MOV EAX,b@Variable@main
CDQ
CMP c@Variable@main,0
JE _division_cero
MOV EBX,c@Variable@main
CDQ
IDIV EBX
MOV var@@aux32,EAX
MOV EAX,a@Variable@main
SUB EAX,var@@aux32
MOV var@@aux33,EAX
CALL salida1
MOV EAX,a@Variable@main
MOV EDX,salida1@Funcion@main
MUL EDX
MOV var@@aux35,EAX
MOV EAX,var@@aux33
ADD EAX,var@@aux35
JO _overflow
MOV var@@aux36,EAX
MOV EAX,var@@aux36
ADD EAX,var@@aux32
JO _overflow
MOV var@@aux38,EAX
MOV EAX,var@@aux38
MOV d@Variable@main,EAX
MOV EAX,d@Variable@main
MOV EDX,20
CMP d@Variable@main,EDX
JNE Label44
invoke MessageBox, NULL, addr cadena@@318, addr cadena@@318, MB_OK
JMP Label45
Label44:
invoke MessageBox, NULL, addr cadena@@319, addr cadena@@319, MB_OK
Label45:
MOV EAX,b@Variable@main
CDQ
CMP c@Variable@main,0
JE _division_cero
MOV EBX,c@Variable@main
CDQ
IDIV EBX
MOV var@@aux45,EAX
MOV EAX,a@Variable@main
ADD EAX,var@@aux45
JO _overflow
MOV var@@aux46,EAX
MOV EAX,var@@aux46
ADD EAX,var@@aux45
JO _overflow
MOV var@@aux48,EAX
MOV EAX,var@@aux48
ADD EAX,a@Variable@main
JO _overflow
MOV var@@aux49,EAX
MOV EAX,var@@aux49
MOV d@Variable@main,EAX
CALL salida1
MOV EAX,22
CDQ
CMP salida1@Funcion@main,0
JE _division_cero
MOV EBX, salida1@Funcion@main
MOV EBX,salida1@Funcion@main
CDQ
IDIV EBX
MOV var@@aux52,EAX
MOV EAX,d@Variable@main
MOV EAX,var@@aux52
CMP d@Variable@main,EAX
JNE Label57
invoke MessageBox, NULL, addr cadena@@320, addr cadena@@320, MB_OK
JMP Label58
Label57:
invoke MessageBox, NULL, addr cadena@@321, addr cadena@@321, MB_OK
Label58:
MOV c@Variable@main,0
MOV EAX,b@Variable@main
CDQ
CMP c@Variable@main,0
JE _division_cero
MOV EBX,c@Variable@main
CDQ
IDIV EBX
MOV var@@aux59,EAX
MOV EAX,a@Variable@main
ADD EAX,var@@aux59
JO _overflow
MOV var@@aux60,EAX
MOV EAX,var@@aux60
ADD EAX,var@@aux59
JO _overflow
MOV var@@aux62,EAX
MOV EAX,var@@aux62
ADD EAX,a@Variable@main
JO _overflow
MOV var@@aux63,EAX
MOV EAX,var@@aux63
MOV d@Variable@main,EAX
MOV EAX,d@Variable@main
MOV EDX,0
CMP d@Variable@main,EDX
JNE Label69
invoke MessageBox, NULL, addr cadena@@322, addr cadena@@322, MB_OK
JMP Label70
Label69:
invoke MessageBox, NULL, addr cadena@@323, addr cadena@@323, MB_OK
Label70:
invoke ExitProcess, 0
end start