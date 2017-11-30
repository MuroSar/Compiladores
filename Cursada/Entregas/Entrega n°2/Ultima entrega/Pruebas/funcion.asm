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
ab1@Variable DD ?
var@@aux2 DD ?
var@@aux0 DD ?
salida1@Funcion DD ?
valor@Variable DD ?
Es_un_9 DB "Es un 9", 0
No_es_un_9 DB "No es un 9", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida1 proc
MOV EAX,6
ADD EAX,3
JO _overflow
MOV var@@aux0,EAX
MOV EAX,var@@aux0
MOV ab1@Variable,EAX
MOV EAX,ab1@Variable
MOV salida1@Funcion,EAX
RET
salida1 endp
start:
FNINIT
CALL salida1
MOV EAX,salida1@Funcion
MOV valor@Variable,EAX
MOV EDX,9
CMP valor@Variable,EDX
JNE Label9
invoke MessageBox, NULL, addr Es_un_9, addr Es_un_9, MB_OK
JMP Label10
Label9:
invoke MessageBox, NULL, addr No_es_un_9, addr No_es_un_9, MB_OK
Label10:
invoke ExitProcess, 0
FNINIT
end start