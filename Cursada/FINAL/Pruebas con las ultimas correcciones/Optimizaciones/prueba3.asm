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
salida1@Funcion@main DD ?
var@@aux18 DD ?
a@Variable@main DD ?
l@Variable@main@salida1 DD ?
e@Variable@main@salida1 DD ?
var@@aux9 DD ?
var@@aux8 DD ?
var@@aux60 DD ?
var@@aux7 DD ?
var@@aux61 DD ?
var@@aux20 DD ?
var@@aux64 DD ?
var@@aux22 DD ?
var@@aux47 DD ?
var@@aux49 DD ?
c@Variable@main DD ?
f@Variable@main@salida1 DD ?
var@@aux10 DD ?
var@@aux13 DD ?
var@@aux57 DD ?
var@@aux12 DD ?
var@@aux56 DD ?
var@@aux36 DD ?
d@Variable@main DD ?
var@@aux44 DD ?
var@@aux43 DD ?
var@@aux46 DD ?
var@@aux29 DD ?
j@Variable@main@salida1 DD ?
b@Variable@main DD ?
var@@aux71 DD ?
var@@aux72 DD ?
var@@aux75 DD ?
var@@aux30 DD ?
var@@aux74 DD ?
var@@aux33 DD ?
var@@aux32 DD ?
var@@aux35 DD ?
k@Variable@main@salida1 DD ?
cadena@@324 DB "Optimiza 1", 0
cadena@@325 DB "Error 1", 0
cadena@@326 DB "Optimiza 2", 0
cadena@@327 DB "Error 2", 0
cadena@@328 DB "Optimiza 3", 0
cadena@@329 DB "Error 3", 0
cadena@@330 DB "Optimiza 4", 0
cadena@@331 DB "Error 4", 0
cadena@@332 DB "Nunca llega if", 0
cadena@@333 DB "nunca llega else", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida1 proc
MOV j@Variable@main@salida1,3
MOV k@Variable@main@salida1,4
MOV f@Variable@main@salida1,2
MOV l@Variable@main@salida1,7
MOV EAX,j@Variable@main@salida1
MOV EDX,f@Variable@main@salida1
MUL EDX
MOV var@@aux7,EAX
MOV EAX,f@Variable@main@salida1
ADD EAX,var@@aux7
JO _overflow
MOV var@@aux8,EAX
MOV EAX,var@@aux8
ADD EAX,k@Variable@main@salida1
JO _overflow
MOV var@@aux9,EAX
MOV EAX,var@@aux9
SUB EAX,l@Variable@main@salida1
MOV var@@aux10,EAX
MOV EAX,var@@aux10
ADD EAX,var@@aux7
JO _overflow
MOV var@@aux12,EAX
MOV EAX,var@@aux12
SUB EAX,1
MOV var@@aux13,EAX
MOV EAX,var@@aux13
MOV e@Variable@main@salida1,EAX
MOV EAX,e@Variable@main@salida1
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
MOV var@@aux16,EAX
MOV EAX,a@Variable@main
ADD EAX,var@@aux16
JO _overflow
MOV var@@aux17,EAX
MOV EAX,var@@aux17
ADD EAX,a@Variable@main
JO _overflow
MOV var@@aux18,EAX
MOV EAX,var@@aux18
ADD EAX,var@@aux16
JO _overflow
MOV var@@aux20,EAX
CALL salida1
MOV EAX,var@@aux20
SUB EAX,salida1@Funcion@main
MOV var@@aux22,EAX
MOV EAX,var@@aux22
MOV d@Variable@main,EAX
MOV EAX,d@Variable@main
MOV EDX,210
CMP d@Variable@main,EDX
JNE Label28
invoke MessageBox, NULL, addr cadena@@324, addr cadena@@324, MB_OK
JMP Label29
Label28:
invoke MessageBox, NULL, addr cadena@@325, addr cadena@@325, MB_OK
Label29:
MOV EAX,b@Variable@main
MOV EDX,c@Variable@main
MUL EDX
MOV var@@aux29,EAX
MOV EAX,a@Variable@main
SUB EAX,var@@aux29
MOV var@@aux30,EAX
CALL salida1
MOV EAX,var@@aux30
ADD EAX,salida1@Funcion@main
JO _overflow
MOV var@@aux32,EAX
MOV EAX,var@@aux32
ADD EAX,a@Variable@main
JO _overflow
MOV var@@aux33,EAX
MOV EAX,var@@aux33
SUB EAX,var@@aux29
MOV var@@aux35,EAX
MOV EAX,var@@aux35
ADD EAX,a@Variable@main
JO _overflow
MOV var@@aux36,EAX
MOV EAX,var@@aux36
MOV d@Variable@main,EAX
MOV EAX,d@Variable@main
MOV EDX,-160
CMP d@Variable@main,EDX
JNE Label42
invoke MessageBox, NULL, addr cadena@@326, addr cadena@@326, MB_OK
JMP Label43
Label42:
invoke MessageBox, NULL, addr cadena@@327, addr cadena@@327, MB_OK
Label43:
MOV EAX,b@Variable@main
CDQ
CMP c@Variable@main,0
JE _division_cero
MOV EBX,c@Variable@main
CDQ
IDIV EBX
MOV var@@aux43,EAX
MOV EAX,a@Variable@main
SUB EAX,var@@aux43
MOV var@@aux44,EAX
CALL salida1
MOV EAX,a@Variable@main
MOV EDX,salida1@Funcion@main
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
MOV d@Variable@main,EAX
MOV EAX,d@Variable@main
MOV EDX,110
CMP d@Variable@main,EDX
JNE Label55
invoke MessageBox, NULL, addr cadena@@328, addr cadena@@328, MB_OK
JMP Label56
Label55:
invoke MessageBox, NULL, addr cadena@@329, addr cadena@@329, MB_OK
Label56:
MOV EAX,b@Variable@main
CDQ
CMP c@Variable@main,0
JE _division_cero
MOV EBX,c@Variable@main
CDQ
IDIV EBX
MOV var@@aux56,EAX
MOV EAX,a@Variable@main
ADD EAX,var@@aux56
JO _overflow
MOV var@@aux57,EAX
MOV EAX,var@@aux57
ADD EAX,var@@aux56
JO _overflow
MOV var@@aux59,EAX
MOV EAX,var@@aux59
ADD EAX,a@Variable@main
JO _overflow
MOV var@@aux60,EAX
MOV EAX,var@@aux60
SUB EAX,20
MOV var@@aux61,EAX
MOV EAX,var@@aux61
MOV d@Variable@main,EAX
CALL salida1
MOV EAX,20
CDQ
CMP salida1@Funcion@main,0
JE _division_cero
MOV EBX, salida1@Funcion@main
MOV EBX,salida1@Funcion@main
CDQ
IDIV EBX
MOV var@@aux64,EAX
MOV EAX,d@Variable@main
MOV EAX,var@@aux64
CMP d@Variable@main,EAX
JNE Label69
invoke MessageBox, NULL, addr cadena@@330, addr cadena@@330, MB_OK
JMP Label70
Label69:
invoke MessageBox, NULL, addr cadena@@331, addr cadena@@331, MB_OK
Label70:
MOV c@Variable@main,0
MOV EAX,b@Variable@main
CDQ
CMP c@Variable@main,0
JE _division_cero
MOV EBX,c@Variable@main
CDQ
IDIV EBX
MOV var@@aux71,EAX
MOV EAX,a@Variable@main
ADD EAX,var@@aux71
JO _overflow
MOV var@@aux72,EAX
MOV EAX,var@@aux72
ADD EAX,var@@aux71
JO _overflow
MOV var@@aux74,EAX
MOV EAX,var@@aux74
ADD EAX,a@Variable@main
JO _overflow
MOV var@@aux75,EAX
MOV EAX,var@@aux75
MOV d@Variable@main,EAX
MOV EAX,d@Variable@main
MOV EDX,0
CMP d@Variable@main,EDX
JNE Label81
invoke MessageBox, NULL, addr cadena@@332, addr cadena@@332, MB_OK
JMP Label82
Label81:
invoke MessageBox, NULL, addr cadena@@333, addr cadena@@333, MB_OK
Label82:
invoke ExitProcess, 0
end start