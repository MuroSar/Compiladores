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
var@@aux58 DQ ?
var@@aux18 DQ ?
a@Variable@main DQ ?
salida3@Funcion@main DQ ?
ab1@Variable@main@salida30 DQ ?
var@@aux2 DQ ?
var@@aux65 DQ ?
c@Variable@main DQ ?
var@@aux90 DQ ?
var@@aux51 DQ ?
var@@aux11 DQ ?
salida10@Funcion@main DQ ?
var@@aux38 DQ ?
ab1@Variable@main@salida3 DQ ?
ab1@Variable@main@salida10 DQ ?
var@@aux82 DQ ?
var@@aux40 DQ ?
var@@aux43 DQ ?
var@@aux89 DQ ?
var@@aux25 DQ ?
b@Variable@main DQ ?
salida30@Funcion@main DQ ?
var@@aux73 DQ ?
var@@aux31 DQ ?
cadena@@206 DB "Multiplicaci�n var = -cte anda Double", 0
cadena@@207 DB "Multiplicaci�n var = -cte no anda Double", 0
cadena@@208 DB "Multiplicaci�n var*var = -cte anda Double", 0
cadena@@209 DB "Multiplicaci�n var*var = -cte no anda Double", 0
cadena@@210 DB "Multiplicaci�n var*cte = -cte anda Double", 0
cadena@@211 DB "Multiplicaci�n var*cte = -cte no anda Double", 0
cadena@@212 DB "Multiplicaci�n -cte*var = -cte anda Double", 0
cadena@@213 DB "Multiplicaci�n -cte*var = -cte no anda Double", 0
cadena@@214 DB "Multiplicaci�n cte*-cte = -cte anda Double", 0
cadena@@215 DB "Multiplicaci�n cte*-cte = -cte no anda Double", 0
cadena@@216 DB "Multiplicaci�n var*fn = -cte anda Double", 0
cadena@@217 DB "Multiplicaci�n var*fn = -cte no anda Double", 0
cadena@@218 DB "Multiplicaci�n fn*var = -cte anda Double", 0
cadena@@219 DB "Multiplicaci�n fn*var = -cte no anda Double", 0
cadena@@220 DB "Multiplicaci�n cte*fn = -cte anda Double", 0
cadena@@221 DB "Multiplicaci�n cte*fn = -cte no anda Double", 0
cadena@@222 DB "Multiplicaci�n fn*-cte = -cte anda Double", 0
cadena@@223 DB "Multiplicaci�n fn*-cte = -cte no anda Double", 0
cadena@@224 DB "Multiplicaci�n fn*fn = -cte anda Double", 0
cadena@@225 DB "Multiplicaci�n fn*fn = -cte no anda Double", 0
cadena@@226 DB "Multiplicaci�n var*fn = var anda Double", 0
cadena@@227 DB "Multiplicaci�n var*fn = var no anda Double", 0
cadena@@228 DB "Multiplicaci�n cte*-cte = fn anda Double", 0
cadena@@229 DB "Multiplicaci�n cte*-cte = fn no anda Double", 0
const@@_3_0 DQ -3.0
const@@10_0 DQ 10.0
const@@_30_0 DQ -30.0
const@@_10_0 DQ -10.0
const@@3_0 DQ 3.0
aux_mem_2bytes DW ?
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida3 proc
FLD const@@_3_0
FSTP ab1@Variable@main@salida3
FLD ab1@Variable@main@salida3
FSTP salida3@Funcion@main
RET
salida3 endp
salida10 proc
FLD const@@10_0
FSTP ab1@Variable@main@salida10
FLD ab1@Variable@main@salida10
FSTP salida10@Funcion@main
RET
salida10 endp
salida30 proc
FLD const@@_30_0
FSTP ab1@Variable@main@salida30
FLD ab1@Variable@main@salida30
FSTP salida30@Funcion@main
RET
salida30 endp
start:
FLD const@@_10_0
FSTP a@Variable@main
FLD const@@3_0
FSTP b@Variable@main
FLD a@Variable@main
FLD b@Variable@main
FMUL
FSTP var@@aux2
FLD var@@aux2
FSTP c@Variable@main
FLD c@Variable@main
FLD const@@_30_0
FCOM
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr cadena@@206, addr cadena@@206, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@207, addr cadena@@207, MB_OK
Label9:
FLD const@@_10_0
FSTP a@Variable@main
FLD const@@3_0
FSTP b@Variable@main
FLD a@Variable@main
FLD b@Variable@main
FMUL
FSTP var@@aux11
FLD var@@aux11
FLD const@@_30_0
FCOM
FSTSW AX
SAHF
JNE Label16
invoke MessageBox, NULL, addr cadena@@208, addr cadena@@208, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@209, addr cadena@@209, MB_OK
Label17:
FLD const@@_10_0
FSTP a@Variable@main
FLD a@Variable@main
FLD const@@3_0
FMUL
FSTP var@@aux18
FLD var@@aux18
FLD const@@_30_0
FCOM
FSTSW AX
SAHF
JNE Label23
invoke MessageBox, NULL, addr cadena@@210, addr cadena@@210, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@211, addr cadena@@211, MB_OK
Label24:
FLD const@@3_0
FSTP b@Variable@main
FLD const@@_10_0
FLD b@Variable@main
FMUL
FSTP var@@aux25
FLD var@@aux25
FLD const@@_30_0
FCOM
FSTSW AX
SAHF
JNE Label30
invoke MessageBox, NULL, addr cadena@@212, addr cadena@@212, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@213, addr cadena@@213, MB_OK
Label31:
FLD const@@10_0
FLD const@@_3_0
FMUL
FSTP var@@aux31
FLD var@@aux31
FLD const@@_30_0
FCOM
FSTSW AX
SAHF
JNE Label36
invoke MessageBox, NULL, addr cadena@@214, addr cadena@@214, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@215, addr cadena@@215, MB_OK
Label37:
Label39:
FLD const@@10_0
FSTP a@Variable@main
CALL salida3
FLD a@Variable@main
FLD salida3@Funcion@main
FMUL
FSTP var@@aux43
FLD var@@aux43
FLD const@@_30_0
FCOM
FSTSW AX
SAHF
JNE Label48
invoke MessageBox, NULL, addr cadena@@216, addr cadena@@216, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@217, addr cadena@@217, MB_OK
Label49:
FLD const@@_3_0
FSTP b@Variable@main
CALL salida10
FLD salida10@Funcion@main
FLD b@Variable@main
FMUL
FSTP var@@aux51
FLD var@@aux51
FLD const@@_30_0
FCOM
FSTSW AX
SAHF
JNE Label56
invoke MessageBox, NULL, addr cadena@@218, addr cadena@@218, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@219, addr cadena@@219, MB_OK
Label57:
CALL salida3
FLD const@@10_0
FLD salida3@Funcion@main
FMUL
FSTP var@@aux58
FLD var@@aux58
FLD const@@_30_0
FCOM
FSTSW AX
SAHF
JNE Label63
invoke MessageBox, NULL, addr cadena@@220, addr cadena@@220, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@221, addr cadena@@221, MB_OK
Label64:
CALL salida10
FLD salida10@Funcion@main
FLD const@@_3_0
FMUL
FSTP var@@aux65
FLD var@@aux65
FLD const@@_30_0
FCOM
FSTSW AX
SAHF
JNE Label70
invoke MessageBox, NULL, addr cadena@@222, addr cadena@@222, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@223, addr cadena@@223, MB_OK
Label71:
CALL salida10
CALL salida3
FLD salida10@Funcion@main
FLD salida3@Funcion@main
FMUL
FSTP var@@aux73
FLD var@@aux73
FLD const@@_30_0
FCOM
FSTSW AX
SAHF
JNE Label78
invoke MessageBox, NULL, addr cadena@@224, addr cadena@@224, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@225, addr cadena@@225, MB_OK
Label79:
FLD const@@10_0
FSTP a@Variable@main
FLD const@@_30_0
FSTP c@Variable@main
CALL salida3
FLD a@Variable@main
FLD salida3@Funcion@main
FMUL
FSTP var@@aux82
FLD var@@aux82
FLD c@Variable@main
FCOM
FSTSW AX
SAHF
JNE Label87
invoke MessageBox, NULL, addr cadena@@226, addr cadena@@226, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@227, addr cadena@@227, MB_OK
Label88:
FLD const@@10_0
FLD const@@_3_0
FMUL
FSTP var@@aux90
CALL salida30
FLD var@@aux90
FLD salida30@Funcion@main
FCOM
FSTSW AX
SAHF
JNE Label96
invoke MessageBox, NULL, addr cadena@@228, addr cadena@@228, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@229, addr cadena@@229, MB_OK
Label97:
invoke ExitProcess, 0
end start