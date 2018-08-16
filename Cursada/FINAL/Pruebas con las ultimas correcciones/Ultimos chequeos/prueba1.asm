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
a@Variable@main DD ?
var@@aux9 DD ?
var@@aux6 DD ?
var@@aux5 DD ?
var@@aux7 DD ?
var@@aux4 DD ?
f2@Funcion@main DD ?
f1@Funcion@main DD ?
c@Variable@main@f1 DD ?
c@Variable@main@f2 DD ?
var@@aux10 DD ?
var@@aux12 DD ?
a@Variable@main@f2 DD ?
var@@aux36 DD ?
b@Variable@main@f2 DD ?
var@@aux28 DD ?
var@@aux29 DD ?
d@Variable@main@f2 DD ?
d@Variable@main@f1 DD ?
b@Variable@main DD ?
var@@aux31 DD ?
var@@aux30 DD ?
var@@aux33 DD ?
var@@aux34 DD ?
cadena@@638 DB "Anda 1", 0
cadena@@639 DB "Error 1", 0
cadena@@640 DB "Error 2", 0
cadena@@641 DB "Anda 2", 0
cadena@@642 DB "MOVE: bien", 0
cadena@@643 DB "MOVE: mal", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
f1 proc
MOV b@Variable@main,10
MOV c@Variable@main@f1,1
MOV EAX,a@Variable@main
MOV EDX,b@Variable@main
MUL EDX
MOV var@@aux4,EAX
MOV EAX,var@@aux4
ADD EAX,c@Variable@main@f1
JO _overflow
MOV var@@aux5,EAX
MOV EAX,2
MOV EDX,b@Variable@main
MUL EDX
MOV var@@aux6,EAX
MOV EAX,var@@aux5
ADD EAX,var@@aux6
JO _overflow
MOV var@@aux7,EAX
MOV EAX,var@@aux7
ADD EAX,var@@aux4
JO _overflow
MOV var@@aux9,EAX
MOV EAX,var@@aux9
ADD EAX,c@Variable@main@f1
JO _overflow
MOV var@@aux10,EAX
MOV EAX,var@@aux10
MOV d@Variable@main@f1,EAX
MOV EAX,d@Variable@main@f1
MOV f1@Funcion@main,EAX
RET
f1 endp
f2 proc
MOV a@Variable@main@f2,30
MOV b@Variable@main@f2,10
MOV c@Variable@main@f2,1
MOV EAX,a@Variable@main@f2
MOV EDX,b@Variable@main@f2
MUL EDX
MOV var@@aux28,EAX
MOV EAX,var@@aux28
ADD EAX,c@Variable@main@f2
JO _overflow
MOV var@@aux29,EAX
MOV EAX,2
MOV EDX,b@Variable@main@f2
MUL EDX
MOV var@@aux30,EAX
MOV EAX,var@@aux29
ADD EAX,var@@aux30
JO _overflow
MOV var@@aux31,EAX
MOV EAX,var@@aux31
ADD EAX,var@@aux28
JO _overflow
MOV var@@aux33,EAX
MOV EAX,var@@aux33
ADD EAX,c@Variable@main@f2
JO _overflow
MOV var@@aux34,EAX
MOV EAX,var@@aux34
MOV d@Variable@main@f2,EAX
MOV EAX,d@Variable@main@f2
MOV f2@Funcion@main,EAX
RET
f2 endp
start:
MOV a@Variable@main,1
MOV b@Variable@main,1
Label2:
CALL f1
MOV EAX,f1@Funcion@main
MOV EDX,42
CMP EAX,EDX
JNE Label18
invoke MessageBox, NULL, addr cadena@@638, addr cadena@@638, MB_OK
JMP Label19
Label18:
invoke MessageBox, NULL, addr cadena@@639, addr cadena@@639, MB_OK
Label19:
CALL f1
MOV EAX,f1@Funcion@main
MOV EDX,10
CMP EAX,EDX
JNE Label24
invoke MessageBox, NULL, addr cadena@@640, addr cadena@@640, MB_OK
JMP Label25
Label24:
invoke MessageBox, NULL, addr cadena@@641, addr cadena@@641, MB_OK
Label25:
CALL f2
MOV EAX,f2@Funcion@main
MOV EDX,622
CMP EAX,EDX
JNE Label42
invoke MessageBox, NULL, addr cadena@@642, addr cadena@@642, MB_OK
JMP Label43
Label42:
invoke MessageBox, NULL, addr cadena@@643, addr cadena@@643, MB_OK
Label43:
invoke ExitProcess, 0
end start