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
cadena@@644 DB "IMPRIME", 0
cadena@@645 DB "A", 0
cadena@@646 DB "B", 0
cadena@@647 DB "L", 0
cadena@@648 DB "IMPRIME Otra vez", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
invoke MessageBox, NULL, addr cadena@@644, addr cadena@@644, MB_OK
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
invoke MessageBox, NULL, addr cadena@@645, addr cadena@@645, MB_OK
Label6:
MOV EAX,d@Variable@main
MOV EDX,3
CMP d@Variable@main,EDX
JNE Label10
invoke MessageBox, NULL, addr cadena@@646, addr cadena@@646, MB_OK
invoke MessageBox, NULL, addr cadena@@647, addr cadena@@647, MB_OK
Label10:
invoke MessageBox, NULL, addr cadena@@648, addr cadena@@648, MB_OK
invoke ExitProcess, 0
end start