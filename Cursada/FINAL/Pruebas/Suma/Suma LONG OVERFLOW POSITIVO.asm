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
b@Variable DD ?
a@Variable DD ?
c@Variable DD ?
var@@aux2 DD ?
cadena@@106 DB "Suma var = cte anda Long", 0
cadena@@107 DB "Suma var = cte no anda Long", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
MOV a@Variable,2147483647
MOV b@Variable,2
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV c@Variable,EAX
MOV EAX,c@Variable
MOV EDX,13
CMP c@Variable,EDX
JNE Label8
invoke MessageBox, NULL, addr cadena@@106, addr cadena@@106, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@107, addr cadena@@107, MB_OK
Label9:
invoke ExitProcess, 0
end start