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
b@Variable DD ?
a@Variable DD ?
var@@aux2 DD ?
dividebien DB "divide bien", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
MOV a@Variable,10
MOV b@Variable,5
MOV EAX,a@Variable
CDQ
CMP b@Variable,0
JE _division_cero
MOV EBX,b@Variable
DIV EBX
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV a@Variable,EAX
MOV EDX,2
CMP a@Variable,EDX
JNE Label7
invoke MessageBox, NULL, addr dividebien, addr dividebien, MB_OK
Label7:
invoke ExitProcess, 0
end start