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
var@@aux2 DQ ?
var@@aux65 DQ ?
salida13@Funcion@main DQ ?
c@Variable@main DQ ?
var@@aux90 DQ ?
var@@aux51 DQ ?
var@@aux11 DQ ?
salida10@Funcion@main DQ ?
var@@aux38 DQ ?
ab1@Variable@main@salida3 DQ ?
ab1@Variable@main@salida13 DQ ?
ab1@Variable@main@salida10 DQ ?
var@@aux82 DQ ?
var@@aux40 DQ ?
var@@aux43 DQ ?
var@@aux89 DQ ?
var@@aux25 DQ ?
b@Variable@main DQ ?
var@@aux73 DQ ?
var@@aux31 DQ ?
cadena@@358 DB "Resta var = -cte anda Double", 0
cadena@@359 DB "Resta var = -cte no anda Double", 0
cadena@@360 DB "Resta var-var = -cte anda Double", 0
cadena@@361 DB "Resta var-var = -cte no anda Double", 0
cadena@@362 DB "Resta var-cte = -cte anda Double", 0
cadena@@363 DB "Resta var-cte = -cte no anda Double", 0
cadena@@364 DB "Resta -cte-var = -cte anda Double", 0
cadena@@365 DB "Resta -cte-var = -cte no anda Double", 0
cadena@@366 DB "Resta -cte-cte = -cte anda Double", 0
cadena@@367 DB "Resta -cte-cte = -cte no anda Double", 0
cadena@@368 DB "Resta var-fn = -cte anda Double", 0
cadena@@369 DB "Resta var-fn = -cte no anda Double", 0
cadena@@370 DB "Resta fn-var = -cte anda Double", 0
cadena@@371 DB "Resta fn-var = -cte no anda Double", 0
cadena@@372 DB "Resta -cte-fn = -cte anda Double", 0
cadena@@373 DB "Resta -cte-fn = -cte no anda Double", 0
cadena@@374 DB "Resta fn-cte = -cte anda Double", 0
cadena@@375 DB "Resta fn-cte = -cte no anda Double", 0
cadena@@376 DB "Resta fn-fn = -cte anda Double", 0
cadena@@377 DB "Resta fn-fn = -cte no anda Double", 0
cadena@@378 DB "Resta var-fn = var anda Double", 0
cadena@@379 DB "Resta var-fn = var no anda Double", 0
cadena@@380 DB "Resta -cte-cte = fn anda Double", 0
cadena@@381 DB "Resta -cte-cte = fn no anda Double", 0
const@@3_0 DQ 3.0
const@@_10_0 DQ -10.0
const@@_13_0 DQ -13.0
aux_mem_2bytes DW ?
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida3 proc
FLD const@@3_0
FSTP ab1@Variable@main@salida3
FLD ab1@Variable@main@salida3
FSTP salida3@Funcion@main
RET
salida3 endp
salida10 proc
FLD const@@_10_0
FSTP ab1@Variable@main@salida10
FLD ab1@Variable@main@salida10
FSTP salida10@Funcion@main
RET
salida10 endp
salida13 proc
FLD const@@_13_0
FSTP ab1@Variable@main@salida13
FLD ab1@Variable@main@salida13
FSTP salida13@Funcion@main
RET
salida13 endp
start:
FLD const@@_10_0
FSTP a@Variable@main
FLD const@@3_0
FSTP b@Variable@main
FLD a@Variable@main
FLD b@Variable@main
FSUB
FSTP var@@aux2
FLD var@@aux2
FSTP c@Variable@main
FLD c@Variable@main
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr cadena@@358, addr cadena@@358, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@359, addr cadena@@359, MB_OK
Label9:
FLD const@@_10_0
FSTP a@Variable@main
FLD const@@3_0
FSTP b@Variable@main
FLD a@Variable@main
FLD b@Variable@main
FSUB
FSTP var@@aux11
FLD var@@aux11
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label16
invoke MessageBox, NULL, addr cadena@@360, addr cadena@@360, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@361, addr cadena@@361, MB_OK
Label17:
FLD const@@_10_0
FSTP a@Variable@main
FLD a@Variable@main
FLD const@@3_0
FSUB
FSTP var@@aux18
FLD var@@aux18
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label23
invoke MessageBox, NULL, addr cadena@@362, addr cadena@@362, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@363, addr cadena@@363, MB_OK
Label24:
FLD const@@3_0
FSTP b@Variable@main
FLD const@@_10_0
FLD b@Variable@main
FSUB
FSTP var@@aux25
FLD var@@aux25
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label30
invoke MessageBox, NULL, addr cadena@@364, addr cadena@@364, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@365, addr cadena@@365, MB_OK
Label31:
FLD const@@_10_0
FLD const@@3_0
FSUB
FSTP var@@aux31
FLD var@@aux31
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label36
invoke MessageBox, NULL, addr cadena@@366, addr cadena@@366, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@367, addr cadena@@367, MB_OK
Label37:
Label39:
FLD const@@_10_0
FSTP a@Variable@main
CALL salida3
FLD a@Variable@main
FLD salida3@Funcion@main
FSUB
FSTP var@@aux43
FLD var@@aux43
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label48
invoke MessageBox, NULL, addr cadena@@368, addr cadena@@368, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@369, addr cadena@@369, MB_OK
Label49:
FLD const@@3_0
FSTP b@Variable@main
CALL salida10
FLD salida10@Funcion@main

FLD b@Variable@main
FSUB
FSTP var@@aux51
FLD var@@aux51
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label56
invoke MessageBox, NULL, addr cadena@@370, addr cadena@@370, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@371, addr cadena@@371, MB_OK
Label57:
CALL salida3
FLD const@@_10_0
FLD salida3@Funcion@main
FSUB
FSTP var@@aux58
FLD var@@aux58
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label63
invoke MessageBox, NULL, addr cadena@@372, addr cadena@@372, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@373, addr cadena@@373, MB_OK
Label64:
CALL salida10
FLD salida10@Funcion@main

FLD const@@3_0
FSUB
FSTP var@@aux65
FLD var@@aux65
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label70
invoke MessageBox, NULL, addr cadena@@374, addr cadena@@374, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@375, addr cadena@@375, MB_OK
Label71:
CALL salida10
CALL salida3
FLD salida10@Funcion@main

FLD salida3@Funcion@main
FSUB
FSTP var@@aux73
FLD var@@aux73
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label78
invoke MessageBox, NULL, addr cadena@@376, addr cadena@@376, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@377, addr cadena@@377, MB_OK
Label79:
FLD const@@_10_0
FSTP a@Variable@main
FLD const@@_13_0
FSTP c@Variable@main
CALL salida3
FLD a@Variable@main
FLD salida3@Funcion@main
FSUB
FSTP var@@aux82
FLD var@@aux82
FLD c@Variable@main
FCOM
FSTSW AX
SAHF
JNE Label87
invoke MessageBox, NULL, addr cadena@@378, addr cadena@@378, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@379, addr cadena@@379, MB_OK
Label88:
FLD const@@_10_0
FLD const@@3_0
FSUB
FSTP var@@aux90
CALL salida13
FLD salida13@Funcion@main
FCOM
FSTSW AX
SAHF
JNE Label96
invoke MessageBox, NULL, addr cadena@@380, addr cadena@@380, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@381, addr cadena@@381, MB_OK
Label97:
invoke ExitProcess, 0
end start