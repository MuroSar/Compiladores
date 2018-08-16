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
var@@aux14 DD ?
var@@aux16 DD ?
var@@aux19 DD ?
var@@aux18 DD ?
a@Variable@main DD ?
var@@aux5 DD ?
var@@aux7 DD ?
var@@aux4 DD ?
var@@aux3 DD ?
var@@aux49 DD ?
c@Variable@main DD ?
var@@aux50 DD ?
var@@aux53 DD ?
var@@aux52 DD ?
var@@aux37 DD ?
var@@aux38 DD ?
d@Variable@main DD ?
var@@aux40 DD ?
var@@aux41 DD ?
var@@aux26 DD ?
var@@aux28 DD ?
var@@aux27 DD ?
b@Variable@main DD ?
var@@aux30 DD ?
cadena@@304 DB "Optimiza 1", 0
cadena@@305 DB "Error 1", 0
cadena@@306 DB "Optimiza 2", 0
cadena@@307 DB "Error 2", 0
cadena@@308 DB "Optimiza 3", 0
cadena@@309 DB "Error 3", 0
cadena@@310 DB "Optimiza 4", 0
cadena@@311 DB "Error 4", 0
cadena@@312 DB "Nunca llega if", 0
cadena@@313 DB "nunca llega else", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
MOV a@Variable@main,10
MOV b@Variable@main,10
MOV c@Variable@main,10
MOV EAX,b@Variable@main
MOV EDX,c@Variable@main
MUL EDX
MOV var@@aux3,EAX
MOV EAX,a@Variable@main
ADD EAX,var@@aux3
JO _overflow
MOV var@@aux4,EAX
MOV EAX,var@@aux4
ADD EAX,a@Variable@main
JO _overflow
MOV var@@aux5,EAX
MOV EAX,var@@aux5
ADD EAX,var@@aux3
JO _overflow
MOV var@@aux7,EAX
MOV EAX,var@@aux7
MOV d@Variable@main,EAX
MOV EAX,d@Variable@main
MOV EDX,220
CMP d@Variable@main,EDX
JNE Label13
invoke MessageBox, NULL, addr cadena@@304, addr cadena@@304, MB_OK
JMP Label14
Label13:
invoke MessageBox, NULL, addr cadena@@305, addr cadena@@305, MB_OK
Label14:
MOV EAX,b@Variable@main
MOV EDX,c@Variable@main
MUL EDX
MOV var@@aux14,EAX
MOV EAX,a@Variable@main
SUB EAX,var@@aux14
MOV var@@aux15,EAX
MOV EAX,var@@aux15
ADD EAX,a@Variable@main
JO _overflow
MOV var@@aux16,EAX
MOV EAX,var@@aux16
SUB EAX,var@@aux14
MOV var@@aux18,EAX
MOV EAX,var@@aux18
ADD EAX,a@Variable@main
JO _overflow
MOV var@@aux19,EAX
MOV EAX,var@@aux19
MOV d@Variable@main,EAX
MOV EAX,d@Variable@main
MOV EDX,-170
CMP d@Variable@main,EDX
JNE Label25
invoke MessageBox, NULL, addr cadena@@306, addr cadena@@306, MB_OK
JMP Label26
Label25:
invoke MessageBox, NULL, addr cadena@@307, addr cadena@@307, MB_OK
Label26:
MOV EAX,b@Variable@main
CDQ
CMP c@Variable@main,0
JE _division_cero
MOV EBX,c@Variable@main
CDQ
IDIV EBX
MOV var@@aux26,EAX
MOV EAX,a@Variable@main
SUB EAX,var@@aux26
MOV var@@aux27,EAX
MOV EAX,var@@aux27
ADD EAX,a@Variable@main
JO _overflow
MOV var@@aux28,EAX
MOV EAX,var@@aux28
ADD EAX,var@@aux26
JO _overflow
MOV var@@aux30,EAX
MOV EAX,var@@aux30
MOV d@Variable@main,EAX
MOV EAX,d@Variable@main
MOV EDX,20
CMP d@Variable@main,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@308, addr cadena@@308, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@309, addr cadena@@309, MB_OK
Label37:
MOV EAX,b@Variable@main
CDQ
CMP c@Variable@main,0
JE _division_cero
MOV EBX,c@Variable@main
CDQ
IDIV EBX
MOV var@@aux37,EAX
MOV EAX,a@Variable@main
ADD EAX,var@@aux37
JO _overflow
MOV var@@aux38,EAX
MOV EAX,var@@aux38
ADD EAX,var@@aux37
JO _overflow
MOV var@@aux40,EAX
MOV EAX,var@@aux40
ADD EAX,a@Variable@main
JO _overflow
MOV var@@aux41,EAX
MOV EAX,var@@aux41
MOV d@Variable@main,EAX
MOV EAX,d@Variable@main
MOV EDX,22
CMP d@Variable@main,EDX
JNE Label47
invoke MessageBox, NULL, addr cadena@@310, addr cadena@@310, MB_OK
JMP Label48
Label47:
invoke MessageBox, NULL, addr cadena@@311, addr cadena@@311, MB_OK
Label48:
MOV c@Variable@main,0
MOV EAX,b@Variable@main
CDQ
CMP c@Variable@main,0
JE _division_cero
MOV EBX,c@Variable@main
CDQ
IDIV EBX
MOV var@@aux49,EAX
MOV EAX,a@Variable@main
ADD EAX,var@@aux49
JO _overflow
MOV var@@aux50,EAX
MOV EAX,var@@aux50
ADD EAX,var@@aux49
JO _overflow
MOV var@@aux52,EAX
MOV EAX,var@@aux52
ADD EAX,a@Variable@main
JO _overflow
MOV var@@aux53,EAX
MOV EAX,var@@aux53
MOV d@Variable@main,EAX
MOV EAX,d@Variable@main
MOV EDX,0
CMP d@Variable@main,EDX
JNE Label59
invoke MessageBox, NULL, addr cadena@@312, addr cadena@@312, MB_OK
JMP Label60
Label59:
invoke MessageBox, NULL, addr cadena@@313, addr cadena@@313, MB_OK
Label60:
invoke ExitProcess, 0
end start