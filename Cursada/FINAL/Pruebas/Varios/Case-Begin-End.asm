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
d@Variable DD ?
var@@aux1 DD ?
cadena@@0 DB "IMPRIME", 0
cadena@@1 DB "A", 0
cadena@@2 DB "B", 0
cadena@@3 DB "L", 0
cadena@@4 DB "IMPRIME Otra vez", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
invoke MessageBox, NULL, addr cadena@@0, addr cadena@@0, MB_OK
MOV EAX,2
ADD EAX,1
JO _overflow
MOV var@@aux1,EAX
MOV EAX,var@@aux1
MOV d@Variable,EAX
MOV EAX,d@Variable
MOV EDX,2
CMP d@Variable,EDX
JNE Label6
invoke MessageBox, NULL, addr cadena@@1, addr cadena@@1, MB_OK
Label6:
MOV EAX,d@Variable
MOV EDX,3
CMP d@Variable,EDX
JNE Label10
invoke MessageBox, NULL, addr cadena@@2, addr cadena@@2, MB_OK
invoke MessageBox, NULL, addr cadena@@3, addr cadena@@3, MB_OK
Label10:
invoke MessageBox, NULL, addr cadena@@4, addr cadena@@4, MB_OK
invoke ExitProcess, 0
end start