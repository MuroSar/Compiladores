.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
__MIN DD  -2147483648
__MAX DD  2147483647
_msjDC DB "Error: Division por cero", 0
_msjOverflow DB "Error: Overflow", 0
b@Variable DD ?
#sumarRet DD ?
c@Variable DT ?
#sumaRet DD ?
j@Variable DD ?
d@Variable DT ?
a@Variable DD ?
e@Variable DT ?
suma@Funcion DD ?
sumar@Funcion DD ?
"Barbie" DB "Barbie", 0
"Mauro" DB "Mauro", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
MOV R1,5
MOV _a,R1
MOV R1,3
MOV _b,R1
MOV R1,_b
MOV _a,R1
MOV R1,2
MOV _a,R1
MOV R1,4
MOV _b,R1
RET
MOV R1,6,7
MOV _c,R1
MOV R1,4,5
MOV _e,R1
MOV R1,0,0
MOV _d,R1
FTST
JE _division_cero
FLD e@Variable
FLD e@Variable
FDIV
FSTP #aux9
MOV R1,#aux9
MOV _c,R1
MOV R1,6
MOV _j,R1
RET
CALL sumar
MOV R1,#aux13
MOV _a,R1
MOV R2,5
CMP _a,R2
JNE Label18
MOV R1,1
MOV _a,R1
sumar
MOV R2,3
CMP _a,R2
Label18
JNE Label21
MOV R1,2
MOV _a,R1
Label21
CALL suma
MOV R1,#aux21
MOV _a,R1
MOV R2,5
CMP _a,R2
JNE Label27
invoke MessageBox, NULL, addr "Barbie", addr "Barbie", MB_OK
JMP Label28
Label27
invoke MessageBox, NULL, addr "Mauro", addr "Mauro", MB_OK
Label28
MOV R1,6
MOV _a,R1
Label29
invoke ExitProcess, 0
end start