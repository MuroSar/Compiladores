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
var@@aux6 DQ ?
var@@aux5 DQ ?
var@@aux4 DQ ?
var@@aux3 DQ ?
c@Variable@main DQ ?
funcion@Funcion@main DQ ?
j@Variable@main DQ ?
b@Variable@main DQ ?
cadena@@17 DB "Error en el IF", 0
cadena@@18 DB "Anda el IF", 0
cadena@@19 DB " Es un 32,0", 0
cadena@@20 DB "Error", 0
cadena@@21 DB "En el Switch", 0
const@@10_0 DQ 10.0
const@@2_0 DQ 2.0
const@@6_0 DQ 6.0
const@@1145_0 DQ 1145.0
aux_mem_2bytes DW ?
const@@32_0 DQ 32.0
const@@82_0 DQ 82.0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
funcion proc
FLD const@@10_0
FSTP a@Variable@main
FLD const@@2_0
FSTP b@Variable@main
FLD const@@6_0
FSTP c@Variable@main
FLD b@Variable@main
FLD c@Variable@main
FMUL
FSTP var@@aux3
FLD a@Variable@main
FLD var@@aux3
FADD
FSTP var@@aux4
FLD var@@aux4
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@4
FLD var@@aux4
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux4
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@4:
FLD var@@aux4
FLD a@Variable@main
FADD
FSTP var@@aux5
FLD var@@aux5
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@5
FLD var@@aux5
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux5
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@5:
FLD var@@aux5
FSTP funcion@Funcion@main
RET
funcion endp
start:
CALL funcion
FLD funcion@Funcion@main
FLD const@@1145_0
FCOM
FSTSW AX
SAHF
JNE Label12
invoke MessageBox, NULL, addr cadena@@17, addr cadena@@17, MB_OK
JMP Label13
Label12:
invoke MessageBox, NULL, addr cadena@@18, addr cadena@@18, MB_OK
Label13:
CALL funcion
FLD funcion@Funcion@main
FSTP j@Variable@main
FLD j@Variable@main
FLD const@@32_0
FCOM
FSTSW AX
SAHF
JNE Label18
invoke MessageBox, NULL, addr cadena@@19, addr cadena@@19, MB_OK
Label18:
FLD j@Variable@main
FLD const@@82_0
FCOM
FSTSW AX
SAHF
JNE Label22
invoke MessageBox, NULL, addr cadena@@20, addr cadena@@20, MB_OK
invoke MessageBox, NULL, addr cadena@@21, addr cadena@@21, MB_OK
Label22:
invoke ExitProcess, 0
end start