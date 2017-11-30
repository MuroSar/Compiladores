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
b@Variable DQ ?
a@Variable DQ ?
var@@aux2 DQ ?
divide_bien DB "divide bien", 0
const@@10_0 DQ 10.0
const@@5_0 DQ 5.0
const@@2_0 DQ 2.0
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
FLD const@@10_0
FSTP a@Variable
FLD const@@5_0
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
FSTP a@Variable
FLD a@Variable
FLD const@@2_0
FCOM
FSTSW AX
FFREE ST(0)
FFREE ST(1)
FWAIT
SAHF
JNE Label7
invoke MessageBox, NULL, addr divide_bien, addr divide_bien, MB_OK
Label7:
invoke ExitProcess, 0
FNINIT
end start