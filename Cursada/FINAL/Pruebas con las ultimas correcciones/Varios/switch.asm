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
var@Variable@main DD ?
cadena@@651 DB "Es un 4", 0
cadena@@652 DB "No es un 4", 0
cadena@@653 DB "Imprime", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
MOV var@Variable@main,5
MOV EAX,var@Variable@main
MOV EDX,4
CMP var@Variable@main,EDX
JNE Label4
invoke MessageBox, NULL, addr cadena@@651, addr cadena@@651, MB_OK
Label4:
MOV EAX,var@Variable@main
MOV EDX,5
CMP var@Variable@main,EDX
JNE Label7
invoke MessageBox, NULL, addr cadena@@652, addr cadena@@652, MB_OK
Label7:
invoke MessageBox, NULL, addr cadena@@653, addr cadena@@653, MB_OK
invoke ExitProcess, 0
end start