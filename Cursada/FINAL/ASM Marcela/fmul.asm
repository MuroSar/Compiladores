.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
Divide_bien DB "Multiplica bien", 0
Divide_mal DB "Multiplica mal", 0
const@@20 DQ 20.0
const@@10 DQ 10.0
const@@200 DQ 200.0
vara DQ ?
varb DQ ?
varc DQ ?
.code
start:
FLD const@@20
FST vara
FLD const@@10
FST varb
FLD vara
FLD varb
Fmul
FLD const@@200
Fcom
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr Divide_bien, addr Divide_bien, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr Divide_mal, addr Divide_mal, MB_OK
Label9:
invoke ExitProcess, 0
end start