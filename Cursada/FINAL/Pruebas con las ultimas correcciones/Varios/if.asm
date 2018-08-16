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
pepe@Variable@main DD ?
var@Variable@main DD ?
cadena@@649 DB "var", 0
cadena@@650 DB "Imprime", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
MOV var@Variable@main,8
MOV pepe@Variable@main,7
MOV EAX,var@Variable@main
MOV EAX,pepe@Variable@main
CMP var@Variable@main,EAX
JGE Label5
invoke MessageBox, NULL, addr cadena@@649, addr cadena@@649, MB_OK
Label5:
invoke MessageBox, NULL, addr cadena@@650, addr cadena@@650, MB_OK
invoke ExitProcess, 0
end start