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
var@@aux5 DD ?
var@@aux7 DD ?
var@@aux4 DD ?
var@@aux3 DD ?
b@Variable DD ?
var@@aux49 DD ?
c@Variable DD ?
var@@aux50 DD ?
var@@aux53 DD ?
var@@aux52 DD ?
var@@aux37 DD ?
var@@aux38 DD ?
d@Variable DD ?
a@Variable DD ?
var@@aux40 DD ?
var@@aux41 DD ?
var@@aux26 DD ?
var@@aux28 DD ?
var@@aux27 DD ?
var@@aux30 DD ?
cadena@@0 DB "Optimiza 1", 0
cadena@@1 DB "Error 1", 0
cadena@@2 DB "Optimiza 2", 0
cadena@@3 DB "Error 2", 0
cadena@@4 DB "Optimiza 3", 0
cadena@@5 DB "Error 3", 0
cadena@@6 DB "Optimiza 4", 0
cadena@@7 DB "Error 4", 0
cadena@@8 DB "Nunca llega if", 0
cadena@@9 DB "nunca llega else", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
MOV a@Variable,10
MOV b@Variable,10
MOV c@Variable,10
MOV EAX,b@Variable
MOV EDX,c@Variable
MUL EDX
MOV var@@aux3,EAX
MOV EAX,a@Variable
ADD EAX,var@@aux3
JO _overflow
MOV var@@aux4,EAX
MOV EAX,var@@aux4
ADD EAX,a@Variable
JO _overflow
MOV var@@aux5,EAX
MOV EAX,var@@aux5
ADD EAX,var@@aux3
JO _overflow
MOV var@@aux7,EAX
MOV EAX,var@@aux7
MOV d@Variable,EAX
MOV EAX,d@Variable
MOV EDX,220
CMP d@Variable,EDX
JNE Label13
invoke MessageBox, NULL, addr cadena@@0, addr cadena@@0, MB_OK
JMP Label14
Label13:
invoke MessageBox, NULL, addr cadena@@1, addr cadena@@1, MB_OK
Label14:
MOV EAX,b@Variable
MOV EDX,c@Variable
MUL EDX
MOV var@@aux14,EAX
MOV EAX,a@Variable
SUB EAX,var@@aux14
MOV var@@aux15,EAX
MOV EAX,var@@aux15
ADD EAX,a@Variable
JO _overflow
MOV var@@aux16,EAX
MOV EAX,var@@aux16
SUB EAX,var@@aux14
MOV var@@aux18,EAX
MOV EAX,var@@aux18
ADD EAX,a@Variable
JO _overflow
MOV var@@aux19,EAX
MOV EAX,var@@aux19
MOV d@Variable,EAX
MOV EAX,d@Variable
MOV EDX,-170
CMP d@Variable,EDX
JNE Label25
invoke MessageBox, NULL, addr cadena@@2, addr cadena@@2, MB_OK
JMP Label26
Label25:
invoke MessageBox, NULL, addr cadena@@3, addr cadena@@3, MB_OK
Label26:
MOV EAX,b@Variable
CDQ
CMP c@Variable,0
JE _division_cero
MOV EBX,c@Variable
CDQ
IDIV EBX
MOV var@@aux26,EAX
MOV EAX,a@Variable
SUB EAX,var@@aux26
MOV var@@aux27,EAX
MOV EAX,var@@aux27
ADD EAX,a@Variable
JO _overflow
MOV var@@aux28,EAX
MOV EAX,var@@aux28
ADD EAX,var@@aux26
JO _overflow
MOV var@@aux30,EAX
MOV EAX,var@@aux30
MOV d@Variable,EAX
MOV EAX,d@Variable
MOV EDX,20
CMP d@Variable,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@4, addr cadena@@4, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@5, addr cadena@@5, MB_OK
Label37:
MOV EAX,b@Variable
CDQ
CMP c@Variable,0
JE _division_cero
MOV EBX,c@Variable
CDQ
IDIV EBX
MOV var@@aux37,EAX
MOV EAX,a@Variable
ADD EAX,var@@aux37
JO _overflow
MOV var@@aux38,EAX
MOV EAX,var@@aux38
ADD EAX,var@@aux37
JO _overflow
MOV var@@aux40,EAX
MOV EAX,var@@aux40
ADD EAX,a@Variable
JO _overflow
MOV var@@aux41,EAX
MOV EAX,var@@aux41
MOV d@Variable,EAX
MOV EAX,d@Variable
MOV EDX,22
CMP d@Variable,EDX
JNE Label47
invoke MessageBox, NULL, addr cadena@@6, addr cadena@@6, MB_OK
JMP Label48
Label47:
invoke MessageBox, NULL, addr cadena@@7, addr cadena@@7, MB_OK
Label48:
MOV c@Variable,0
MOV EAX,b@Variable
CDQ
CMP c@Variable,0
JE _division_cero
MOV EBX,c@Variable
CDQ
IDIV EBX
MOV var@@aux49,EAX
MOV EAX,a@Variable
ADD EAX,var@@aux49
JO _overflow
MOV var@@aux50,EAX
MOV EAX,var@@aux50
ADD EAX,var@@aux49
JO _overflow
MOV var@@aux52,EAX
MOV EAX,var@@aux52
ADD EAX,a@Variable
JO _overflow
MOV var@@aux53,EAX
MOV EAX,var@@aux53
MOV d@Variable,EAX
MOV EAX,d@Variable
MOV EDX,0
CMP d@Variable,EDX
JNE Label59
invoke MessageBox, NULL, addr cadena@@8, addr cadena@@8, MB_OK
JMP Label60
Label59:
invoke MessageBox, NULL, addr cadena@@9, addr cadena@@9, MB_OK
Label60:
invoke ExitProcess, 0
end start