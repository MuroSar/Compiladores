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
d@Variable DD ?
var@@aux1 DD ?
IMPRIME DB "IMPRIME", 0
A DB "A", 0
B DB "B", 0
L DB "L", 0
IMPRIME_Otra_vez DB "IMPRIME Otra vez", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
FNINIT
invoke MessageBox, NULL, addr IMPRIME, addr IMPRIME, MB_OK
MOV EAX,2
ADD EAX,1
JO _overflow
MOV var@@aux1,EAX
MOV EAX,var@@aux1
MOV d@Variable,EAX
MOV EDX,2
CMP d@Variable,EDX
JNE Label6
invoke MessageBox, NULL, addr A, addr A, MB_OK
Label6:
MOV EDX,3
CMP d@Variable,EDX
JNE Label10
invoke MessageBox, NULL, addr B, addr B, MB_OK
invoke MessageBox, NULL, addr L, addr L, MB_OK
Label10:
invoke MessageBox, NULL, addr IMPRIME_Otra_vez, addr IMPRIME_Otra_vez, MB_OK
invoke ExitProcess, 0
FNINIT
end start