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
var@@aux2 DQ ?
b@Variable DQ ?
a@Variable DQ ?
z@Variable DQ ?
divide_bien DB "divide bien", 0
divide_mal DB "divide mal", 0
const@@15_5 DQ 15.5
const@@2_5 DQ 2.5
const@@6_2 DQ 6.2
aux_mem_2bytes DW ?
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
FNINIT
FLD const@@15_5
FSTP a@Variable
FLD const@@2_5
FSTP b@Variable
FLD a@Variable
FLD b@Variable
FTST
FSTSW AX
FFREE ST(0)
FFREE ST(1)
FWAIT
SAHF
JE _division_cero
FDIV
FSTP var@@aux2
FLD var@@aux2
FSTP z@Variable
FLD z@Variable
FLD const@@6_2
FCOM
FSTSW AX
FFREE ST(0)
FFREE ST(1)
FWAIT
SAHF
JNE Label8
invoke MessageBox, NULL, addr divide_bien, addr divide_bien, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr divide_mal, addr divide_mal, MB_OK
Label9:
invoke ExitProcess, 0
FNINIT
end start