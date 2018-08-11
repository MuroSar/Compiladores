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
cadena@@180 DB "Multiplicación var = -cte anda Double", 0
cadena@@181 DB "Multiplicación var = -cte no anda Double", 0
cadena@@182 DB "Multiplicación var*var = -cte anda Double", 0
cadena@@183 DB "Multiplicación var*var = -cte no anda Double", 0
cadena@@184 DB "Multiplicación var*cte = -cte anda Double", 0
cadena@@185 DB "Multiplicación var*cte = -cte no anda Double", 0
cadena@@186 DB "Multiplicación -cte*var = -cte anda Double", 0
cadena@@187 DB "Multiplicación -cte*var = -cte no anda Double", 0
cadena@@188 DB "Multiplicación cte*-cte = -cte anda Double", 0
cadena@@189 DB "Multiplicación cte*-cte = -cte no anda Double", 0
cadena@@190 DB "Multiplicación var*fn = -cte anda Double", 0
cadena@@191 DB "Multiplicación var*fn = -cte no anda Double", 0
cadena@@192 DB "Multiplicación fn*var = -cte anda Double", 0
cadena@@193 DB "Multiplicación fn*var = -cte no anda Double", 0
cadena@@194 DB "Multiplicación cte*fn = -cte anda Double", 0
cadena@@195 DB "Multiplicación cte*fn = -cte no anda Double", 0
cadena@@196 DB "Multiplicación fn*-cte = -cte anda Double", 0
cadena@@197 DB "Multiplicación fn*-cte = -cte no anda Double", 0
cadena@@198 DB "Multiplicación fn*fn = -cte anda Double", 0
cadena@@199 DB "Multiplicación fn*fn = -cte no anda Double", 0
cadena@@200 DB "Multiplicación var*fn = var anda Double", 0
cadena@@201 DB "Multiplicación var*fn = var no anda Double", 0
cadena@@202 DB "Multiplicación cte*-cte = fn anda Double", 0
cadena@@203 DB "Multiplicación cte*-cte = fn no anda Double", 0
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
invoke MessageBox, NULL, addr cadena@@180, addr cadena@@180, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@181, addr cadena@@181, MB_OK
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
invoke MessageBox, NULL, addr cadena@@182, addr cadena@@182, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@183, addr cadena@@183, MB_OK
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
invoke MessageBox, NULL, addr cadena@@184, addr cadena@@184, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@185, addr cadena@@185, MB_OK
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
invoke MessageBox, NULL, addr cadena@@186, addr cadena@@186, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@187, addr cadena@@187, MB_OK
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
invoke MessageBox, NULL, addr cadena@@188, addr cadena@@188, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@189, addr cadena@@189, MB_OK
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
invoke MessageBox, NULL, addr cadena@@190, addr cadena@@190, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@191, addr cadena@@191, MB_OK
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
invoke MessageBox, NULL, addr cadena@@192, addr cadena@@192, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@193, addr cadena@@193, MB_OK
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
invoke MessageBox, NULL, addr cadena@@194, addr cadena@@194, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@195, addr cadena@@195, MB_OK
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
invoke MessageBox, NULL, addr cadena@@196, addr cadena@@196, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@197, addr cadena@@197, MB_OK
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
invoke MessageBox, NULL, addr cadena@@198, addr cadena@@198, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@199, addr cadena@@199, MB_OK
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
invoke MessageBox, NULL, addr cadena@@200, addr cadena@@200, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@201, addr cadena@@201, MB_OK
Label88:
FLD const@@10_0
FLD const@@_3_0
FMUL
FSTP var@@aux90
CALL salida30
FLD salida30@Funcion@main
FCOM
FSTSW AX
SAHF
JNE Label96
invoke MessageBox, NULL, addr cadena@@202, addr cadena@@202, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@203, addr cadena@@203, MB_OK
Label97:
invoke ExitProcess, 0
end start