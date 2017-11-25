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
pepe@Variable DD ?
var@Variable DD ?
var DB "var", 0
pepe DB "pepe", 0
Imprime DB "Imprime", 0
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
MOV EAX,pepe@Variable
CMP var@Variable,EAX
JGE Label6
invoke MessageBox, NULL, addr var, addr var, MB_OK
JMP Label7
Label6:
invoke MessageBox, NULL, addr pepe, addr pepe, MB_OK
Label7:
invoke MessageBox, NULL, addr Imprime, addr Imprime, MB_OK
Label8:
invoke ExitProcess, 0
end start