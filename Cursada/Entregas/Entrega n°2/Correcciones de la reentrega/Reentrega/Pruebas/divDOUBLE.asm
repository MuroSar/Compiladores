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
b@Variable DT ?
a@Variable DT ?
var@@aux2 DT ?
const@@3_0 DT 3,0
dividebien DB "divide bien", 0
const@@10_0 DT 10,0
const@@5_0 DT 5,0
aux_mem_2bytes DW ?
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
FLD const@@10_0
FSTP a@Variable
FLD const@@5_0
FSTP b@Variable
FLD a@Variable
FLD b@Variable
FLDZ
FCOM
FSTSW AX
SAHF
JE _division_cero
FXCH
FDIV
FSTP var@@aux2
FLD var@@aux2
FSTP a@Variable
FLD a@Variable
FLD const@@3_0
FCOM
FSTSW aux_mem_2bytes
MOV AX, aux_mem_2bytes
SAHF
JL Label7
invoke MessageBox, NULL, addr dividebien, addr dividebien, MB_OK
Label7:
invoke ExitProcess, 0
end start