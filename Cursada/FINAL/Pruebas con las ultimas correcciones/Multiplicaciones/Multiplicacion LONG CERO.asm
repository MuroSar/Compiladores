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
cadena@@230 DB "Multiplicaci�n que da 0 anda Long", 0
cadena@@231 DB "Multiplicaci�n que da 0 no anda Long", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
MOV a@Variable@main,10
MOV b@Variable@main,0
MOV EAX,a@Variable@main
MOV EDX,b@Variable@main
MUL EDX
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV c@Variable@main,EAX
MOV EAX,c@Variable@main
MOV EDX,0
CMP c@Variable@main,EDX
JNE Label8
invoke MessageBox, NULL, addr cadena@@230, addr cadena@@230, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@231, addr cadena@@231, MB_OK
Label9:
invoke ExitProcess, 0
end start