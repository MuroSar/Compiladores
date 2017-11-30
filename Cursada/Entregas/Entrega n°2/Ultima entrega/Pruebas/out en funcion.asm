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
var6@Variable DQ ?
var2@Variable DQ ?
var@@aux9 DQ ?
var@@aux6 DQ ?
var3@Variable DQ ?
var@@aux1 DQ ?
var@@aux3 DQ ?
aa@Funcion DQ ?
var1@Variable DQ ?
var@@aux11 DQ ?
var4@Variable DQ ?
Invocacion_de_la_funcion DB "Invocacion de la funcion", 0
TERMINO DB "TERMINO", 0
const@@50_0 DQ 50.0
const@@1_0 DQ 1.0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
aa proc
FLD const@@50_0
FLD var6@Variable
FFREE ST(0)
FFREE ST(1)
FWAIT
FMUL
FSTP var@@aux1
FLD var@@aux1
FSTP var3@Variable
FLD var6@Variable
FLD const@@1_0
FFREE ST(0)
FFREE ST(1)
FWAIT
FADD
JO _overflow
FSTP var@@aux3
FLD var@@aux3
FSTP var6@Variable
invoke MessageBox, NULL, addr Invocacion_de_la_funcion, addr Invocacion_de_la_funcion, MB_OK
FLD var3@Variable
FSTP aa@Funcion
RET
aa endp
start:
FNINIT
FLD const@@1_0
FSTP var6@Variable
Label1:
CALL aa
CALL aa
FLD aa@Funcion
FLD aa@Funcion
FFREE ST(0)
FFREE ST(1)
FWAIT
FADD
JO _overflow
FSTP var@@aux9
CALL aa
FLD var@@aux9
FLD aa@Funcion
FFREE ST(0)
FFREE ST(1)
FWAIT
FADD
JO _overflow
FSTP var@@aux11
FLD var@@aux11
FSTP var4@Variable
invoke MessageBox, NULL, addr TERMINO, addr TERMINO, MB_OK
invoke ExitProcess, 0
FNINIT
end start