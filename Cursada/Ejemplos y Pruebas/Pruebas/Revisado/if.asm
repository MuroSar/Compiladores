.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
_msjDC DB "Error: Division por cero", 0
_msjOverflow DB "Error: Overflow", 0
var@Variable DD ?
pepe@Variable DD ?
var DB "var", 0
Imprime DB "Imprime", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
FNINIT
MOV var@Variable,8
MOV pepe@Variable,7
MOV EAX,pepe@Variable
CMP var@Variable,EAX
JGE Label5
invoke MessageBox, NULL, addr var, addr var, MB_OK
Label5:
invoke MessageBox, NULL, addr Imprime, addr Imprime, MB_OK
invoke ExitProcess, 0
FNINIT
end start