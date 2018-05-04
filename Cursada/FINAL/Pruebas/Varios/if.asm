.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
__MIN_DOUBLE DQ 2.2250738585072014E-308
__MAX_DOUBLE DQ 1.7976931348623157E308
_msjDC DB "Error: Division por cero", 0
_msjOverflow DB "Error: Overflow", 0
var@Variable DD ?
pepe@Variable DD ?
cadena@@5 DB "var", 0
cadena@@6 DB "Imprime", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
MOV var@Variable,8
MOV pepe@Variable,7
MOV EAX,var@Variable
MOV EAX,pepe@Variable
CMP var@Variable,EAX
JGE Label5
invoke MessageBox, NULL, addr cadena@@5, addr cadena@@5, MB_OK
Label5:
invoke MessageBox, NULL, addr cadena@@6, addr cadena@@6, MB_OK
invoke ExitProcess, 0
end start