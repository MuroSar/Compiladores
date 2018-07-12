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
var@@aux15 DD ?
var@@aux59 DD ?
var@@aux17 DD ?
var@@aux16 DD ?
var@@aux18 DD ?
l@Variable DD ?
f@Variable DD ?
var@@aux9 DD ?
var@@aux8 DD ?
var@@aux60 DD ?
var@@aux7 DD ?
var@@aux61 DD ?
var@@aux20 DD ?
var@@aux64 DD ?
var@@aux22 DD ?
var@@aux47 DD ?
b@Variable DD ?
var@@aux49 DD ?
salida1@Funcion DD ?
c@Variable DD ?
var@@aux10 DD ?
var@@aux13 DD ?
var@@aux57 DD ?
var@@aux12 DD ?
var@@aux56 DD ?
var@@aux36 DD ?
j@Variable DD ?
d@Variable DD ?
a@Variable DD ?
var@@aux44 DD ?
var@@aux43 DD ?
var@@aux46 DD ?
e@Variable DD ?
var@@aux29 DD ?
var@@aux71 DD ?
var@@aux72 DD ?
var@@aux75 DD ?
var@@aux30 DD ?
var@@aux74 DD ?
k@Variable DD ?
var@@aux33 DD ?
var@@aux32 DD ?
var@@aux35 DD ?
cadena@@20 DB "Optimiza 1", 0
cadena@@21 DB "Error 1", 0
cadena@@22 DB "Optimiza 2", 0
cadena@@23 DB "Error 2", 0
cadena@@24 DB "Optimiza 3", 0
cadena@@25 DB "Error 3", 0
cadena@@26 DB "Optimiza 4", 0
cadena@@27 DB "Error 4", 0
cadena@@28 DB "Nunca llega if", 0
cadena@@29 DB "nunca llega else", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida1 proc
MOV j@Variable,3
MOV k@Variable,4
MOV f@Variable,2
MOV l@Variable,7
MOV EAX,j@Variable
MOV EDX,f@Variable
MUL EDX
MOV var@@aux7,EAX
MOV EAX,f@Variable
ADD EAX,var@@aux7
JO _overflow
MOV var@@aux8,EAX
MOV EAX,var@@aux8
ADD EAX,k@Variable
JO _overflow
MOV var@@aux9,EAX
MOV EAX,var@@aux9
SUB EAX,l@Variable
MOV var@@aux10,EAX
MOV EAX,var@@aux10
ADD EAX,var@@aux7
JO _overflow
MOV var@@aux12,EAX
MOV EAX,var@@aux12
SUB EAX,1
MOV var@@aux13,EAX
MOV EAX,var@@aux13
MOV e@Variable,EAX
MOV EAX,e@Variable
MOV salida1@Funcion,EAX
RET
salida1 endp
start:
MOV a@Variable,10
MOV b@Variable,10
MOV c@Variable,10
Label3:
MOV EAX,b@Variable
MOV EDX,c@Variable
MUL EDX
MOV var@@aux16,EAX
MOV EAX,a@Variable
ADD EAX,var@@aux16
JO _overflow
MOV var@@aux17,EAX
MOV EAX,var@@aux17
ADD EAX,a@Variable
JO _overflow
MOV var@@aux18,EAX
MOV EAX,var@@aux18
ADD EAX,var@@aux16
JO _overflow
MOV var@@aux20,EAX
CALL salida1
MOV EAX,var@@aux20
SUB EAX,salida1@Funcion
MOV var@@aux22,EAX
MOV EAX,var@@aux22
MOV d@Variable,EAX
MOV EAX,d@Variable
MOV EDX,210
CMP d@Variable,EDX
JNE Label28
invoke MessageBox, NULL, addr cadena@@20, addr cadena@@20, MB_OK
JMP Label29
Label28:
invoke MessageBox, NULL, addr cadena@@21, addr cadena@@21, MB_OK
Label29:
MOV EAX,b@Variable
MOV EDX,c@Variable
MUL EDX
MOV var@@aux29,EAX
MOV EAX,a@Variable
SUB EAX,var@@aux29
MOV var@@aux30,EAX
CALL salida1
MOV EAX,var@@aux30
ADD EAX,salida1@Funcion
JO _overflow
MOV var@@aux32,EAX
MOV EAX,var@@aux32
ADD EAX,a@Variable
JO _overflow
MOV var@@aux33,EAX
MOV EAX,var@@aux33
SUB EAX,var@@aux29
MOV var@@aux35,EAX
MOV EAX,var@@aux35
ADD EAX,a@Variable
JO _overflow
MOV var@@aux36,EAX
MOV EAX,var@@aux36
MOV d@Variable,EAX
MOV EAX,d@Variable
MOV EDX,-160
CMP d@Variable,EDX
JNE Label42
invoke MessageBox, NULL, addr cadena@@22, addr cadena@@22, MB_OK
JMP Label43
Label42:
invoke MessageBox, NULL, addr cadena@@23, addr cadena@@23, MB_OK
Label43:
MOV EAX,b@Variable
CDQ
CMP c@Variable,0
JE _division_cero
MOV EBX,c@Variable
CDQ
IDIV EBX
MOV var@@aux43,EAX
MOV EAX,a@Variable
SUB EAX,var@@aux43
MOV var@@aux44,EAX
CALL salida1
MOV EAX,a@Variable
MOV EDX,salida1@Funcion
MUL EDX
MOV var@@aux46,EAX
MOV EAX,var@@aux44
ADD EAX,var@@aux46
JO _overflow
MOV var@@aux47,EAX
MOV EAX,var@@aux47
ADD EAX,var@@aux43
JO _overflow
MOV var@@aux49,EAX
MOV EAX,var@@aux49
MOV d@Variable,EAX
MOV EAX,d@Variable
MOV EDX,110
CMP d@Variable,EDX
JNE Label55
invoke MessageBox, NULL, addr cadena@@24, addr cadena@@24, MB_OK
JMP Label56
Label55:
invoke MessageBox, NULL, addr cadena@@25, addr cadena@@25, MB_OK
Label56:
MOV EAX,b@Variable
CDQ
CMP c@Variable,0
JE _division_cero
MOV EBX,c@Variable
CDQ
IDIV EBX
MOV var@@aux56,EAX
MOV EAX,a@Variable
ADD EAX,var@@aux56
JO _overflow
MOV var@@aux57,EAX
MOV EAX,var@@aux57
ADD EAX,var@@aux56
JO _overflow
MOV var@@aux59,EAX
MOV EAX,var@@aux59
ADD EAX,a@Variable
JO _overflow
MOV var@@aux60,EAX
MOV EAX,var@@aux60
SUB EAX,20
MOV var@@aux61,EAX
MOV EAX,var@@aux61
MOV d@Variable,EAX
CALL salida1
MOV EAX,20
CDQ
CMP salida1@Funcion,0
JE _division_cero
MOV EBX, salida1@Funcion
MOV EBX,salida1@Funcion
CDQ
IDIV EBX
MOV var@@aux64,EAX
MOV EAX,d@Variable
MOV EAX,var@@aux64
CMP d@Variable,EAX
JNE Label69
invoke MessageBox, NULL, addr cadena@@26, addr cadena@@26, MB_OK
JMP Label70
Label69:
invoke MessageBox, NULL, addr cadena@@27, addr cadena@@27, MB_OK
Label70:
MOV c@Variable,0
MOV EAX,b@Variable
CDQ
CMP c@Variable,0
JE _division_cero
MOV EBX,c@Variable
CDQ
IDIV EBX
MOV var@@aux71,EAX
MOV EAX,a@Variable
ADD EAX,var@@aux71
JO _overflow
MOV var@@aux72,EAX
MOV EAX,var@@aux72
ADD EAX,var@@aux71
JO _overflow
MOV var@@aux74,EAX
MOV EAX,var@@aux74
ADD EAX,a@Variable
JO _overflow
MOV var@@aux75,EAX
MOV EAX,var@@aux75
MOV d@Variable,EAX
MOV EAX,d@Variable
MOV EDX,0
CMP d@Variable,EDX
JNE Label81
invoke MessageBox, NULL, addr cadena@@28, addr cadena@@28, MB_OK
JMP Label82
Label81:
invoke MessageBox, NULL, addr cadena@@29, addr cadena@@29, MB_OK
Label82:
invoke ExitProcess, 0
end start