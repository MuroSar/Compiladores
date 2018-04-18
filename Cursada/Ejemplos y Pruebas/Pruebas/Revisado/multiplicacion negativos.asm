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
a@Variable DD ?
var@@aux2 DD ?
var@@aux0 DD ?
salida_aux DB "salida aux", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
FNINIT
MOV EAX,-2
MOV EDX,-4
MUL EDX
MOV var@@aux0,EAX
MOV EAX,var@@aux0
MOV a@Variable,EAX
MOV EAX,-2
MOV EDX,-4
MUL EDX
MOV var@@aux2,EAX
MOV EDX,8
CMP var@@aux2,EDX
JNE Label6
invoke MessageBox, NULL, addr salida_aux, addr salida_aux, MB_OK
Label6:
invoke ExitProcess, 0
FNINIT
end start