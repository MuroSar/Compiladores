.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
constCeroLong DD  0
__MIN DD  -2147483648
__MAX DD  2147483647
_msjDC DB "Error: Division por cero", 0
_msjOverflow DB "Error: Overflow", 0
var@Variable DD ?
Esun4 DB "Es un 4", 0
Noesun4 DB "No es un 4", 0
Imprime DB "Imprime", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
MOV var@Variable,5
MOV EDX,4
CMP var@Variable,EDX
JNE Label4
invoke MessageBox, NULL, addr Esun4, addr Esun4, MB_OK
Label4:
MOV EDX,5
CMP var@Variable,EDX
JNE Label7
invoke MessageBox, NULL, addr Noesun4, addr Noesun4, MB_OK
Label7:
invoke MessageBox, NULL, addr Imprime, addr Imprime, MB_OK
Label8:
invoke ExitProcess, 0
end start