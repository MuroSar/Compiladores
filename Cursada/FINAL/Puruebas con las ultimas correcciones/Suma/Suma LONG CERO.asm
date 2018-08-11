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
var@@aux2 DD ?
c@Variable@main DD ?
b@Variable@main DD ?
cadena@@558 DB "Suma que da 0 anda Long", 0
cadena@@559 DB "Suma que da 0 no anda Long", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
MOV a@Variable@main,10
MOV b@Variable@main,-10
MOV EAX,a@Variable@main
ADD EAX,b@Variable@main
JO _overflow
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV c@Variable@main,EAX
MOV EAX,c@Variable@main
MOV EDX,0
CMP c@Variable@main,EDX
JNE Label8
invoke MessageBox, NULL, addr cadena@@558, addr cadena@@558, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@559, addr cadena@@559, MB_OK
Label9:
invoke ExitProcess, 0
end start