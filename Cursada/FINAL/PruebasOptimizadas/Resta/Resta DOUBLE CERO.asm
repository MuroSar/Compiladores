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
var@@aux2 DQ ?
b@Variable DQ ?
c@Variable DQ ?
a@Variable DQ ?
cadena@@172 DB "Resta que da 0 anda Double", 0
cadena@@173 DB "Resta que da 0 no anda Double", 0
const@@10_0 DQ 10.0
const@@0_0 DQ 0.0
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
FST a@Variable
FLD const@@10_0
FST b@Variable
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD var@@aux2
FST c@Variable
FLD c@Variable
FLD const@@0_0
FCOM
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr cadena@@172, addr cadena@@172, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@173, addr cadena@@173, MB_OK
Label9:
invoke ExitProcess, 0
end start