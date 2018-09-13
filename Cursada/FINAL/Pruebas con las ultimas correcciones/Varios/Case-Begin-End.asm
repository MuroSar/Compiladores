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
var@@aux1 DD ?
d@Variable@main DD ?
cadena@@10 DB "IMPRIME", 0
cadena@@11 DB "A", 0
cadena@@12 DB "B", 0
cadena@@13 DB "L", 0
cadena@@14 DB "IMPRIME Otra vez", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
invoke MessageBox, NULL, addr cadena@@10, addr cadena@@10, MB_OK
MOV EAX,2
ADD EAX,1
JO _overflow
MOV var@@aux1,EAX
MOV EAX,var@@aux1
MOV d@Variable@main,EAX
MOV EAX,d@Variable@main
MOV EDX,2
CMP d@Variable@main,EDX
JNE Label6
invoke MessageBox, NULL, addr cadena@@11, addr cadena@@11, MB_OK
Label6:
MOV EAX,d@Variable@main
MOV EDX,3
CMP d@Variable@main,EDX
JNE Label10
invoke MessageBox, NULL, addr cadena@@12, addr cadena@@12, MB_OK
invoke MessageBox, NULL, addr cadena@@13, addr cadena@@13, MB_OK
Label10:
invoke MessageBox, NULL, addr cadena@@14, addr cadena@@14, MB_OK
invoke ExitProcess, 0
end start