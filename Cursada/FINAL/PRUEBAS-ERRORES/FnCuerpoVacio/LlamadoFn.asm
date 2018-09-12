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
a@Variable@main@fn DQ ?
var@@aux1 DQ ?
c@Variable@main DQ ?
b@Variable@main DQ ?
fn@Funcion@main DQ ?
cadena@@13 DB "Error 1", 0
cadena@@14 DB "ANDA", 0
cadena@@15 DB "ANDAAAA", 0
cadena@@16 DB "nada2", 0
const@@10_0 DQ 10.0
const@@11_0 DQ 11.0
aux_mem_2bytes DW ?
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
fn proc
FLD const@@10_0
FSTP a@Variable@main@fn
FLD a@Variable@main@fn
FSTP fn@Funcion@main
RET
fn endp
start:
CALL fn
FLD fn@Funcion@main
FSTP b@Variable@main
CALL fn
FLD fn@Funcion@main
FLD const@@11_0
FCOM
FSTSW AX
SAHF
JNE Label9
invoke MessageBox, NULL, addr cadena@@13, addr cadena@@13, MB_OK
JMP Label10
Label9:
invoke MessageBox, NULL, addr cadena@@14, addr cadena@@14, MB_OK
Label10:
CALL fn
FLD fn@Funcion@main
FLD const@@10_0
FCOM
FSTSW AX
SAHF
JNE Label15
invoke MessageBox, NULL, addr cadena@@15, addr cadena@@15, MB_OK
JMP Label16
Label15:
invoke MessageBox, NULL, addr cadena@@16, addr cadena@@16, MB_OK
Label16:
invoke ExitProcess, 0
end start