.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
__MIN_LONG DD -2147483648
__MAX_LONG DD 2147483647
__MIN_DOUBLE DQ 2.2250738585072014E-308
__MAX_DOUBLE DQ 1.7976931348623157E308
_msjDC DB "Error: Division por cero", 0
_msjOverflow DB "Error: Overflow", 0
a@Variable DQ ?
var@@aux2 DQ ?
b@Variable DQ ?
c@Variable DQ ?
cadena@@0 DB "Suma var = cte anda Double", 0
cadena@@1 DB "Suma var = cte no anda Double", 0
const@@2_2250738585072014E_308 DQ 2.2250738585072014E-308
const@@_2_0 DQ -2.0
const@@13_0 DQ 13.0
aux_mem_2bytes DW ?
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
FLD const@@2_2250738585072014E_308
FST a@Variable
FLD const@@_2_0
FST b@Variable
FLD a@Variable
FLD b@Variable
FADD
FST var@@aux2
FLD var@@aux2
FLD __MIN_DOUBLE
FCOM 
FSTSW AX
SAHF
JLE _overflow
FLD var@@aux2
FLD __MAX_DOUBLE
FCOM 
FSTSW AX
SAHF
JGE _overflow
FLD var@@aux2
FST c@Variable
FLD c@Variable
FLD const@@13_0
FCOM
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr cadena@@0, addr cadena@@0, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@1, addr cadena@@1, MB_OK
Label9:
invoke ExitProcess, 0
end start