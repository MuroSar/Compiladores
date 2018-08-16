.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
__CERO DQ 0.0
__MIN_DOUBLE DQ 2.2250738585072014E-308
__MAX_DOUBLE DQ 1.7976931348623157E308
_msjDC DB "Error: Division por cero", 0
_msjOverflow DB "Error: Overflow", 0
a@Variable@main DQ ?
var@@aux2 DQ ?
c@Variable@main DQ ?
b@Variable@main DQ ?
cadena@@510 DB "Suma var = cte anda Double", 0
cadena@@511 DB "Suma var = cte no anda Double", 0
const@@1_7976931348623157E308 DQ 1.7976931348623157E308
const@@2_0 DQ 2.0
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
FLD const@@1_7976931348623157E308
FSTP a@Variable@main
FLD const@@2_0
FSTP b@Variable@main
FLD a@Variable@main
FLD b@Variable@main
FADD
FSTP var@@aux2
FLD var@@aux2
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@2
FLD var@@aux2
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux2
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@2:
FLD var@@aux2
FSTP c@Variable@main
FLD c@Variable@main
FLD const@@13_0
FCOM
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr cadena@@510, addr cadena@@510, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@511, addr cadena@@511, MB_OK
Label9:
invoke ExitProcess, 0
end start