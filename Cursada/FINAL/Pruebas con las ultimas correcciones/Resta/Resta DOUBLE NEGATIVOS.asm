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
cadena@@336 DB "Resta var = -cte anda Double", 0
cadena@@337 DB "Resta var = -cte no anda Double", 0
cadena@@338 DB "Resta var-var = -cte anda Double", 0
cadena@@339 DB "Resta var-var = -cte no anda Double", 0
cadena@@340 DB "Resta var-cte = -cte anda Double", 0
cadena@@341 DB "Resta var-cte = -cte no anda Double", 0
cadena@@342 DB "Resta -cte-var = -cte anda Double", 0
cadena@@343 DB "Resta -cte-var = -cte no anda Double", 0
cadena@@344 DB "Resta -cte-cte = -cte anda Double", 0
cadena@@345 DB "Resta -cte-cte = -cte no anda Double", 0
cadena@@346 DB "Resta var-fn = -cte anda Double", 0
cadena@@347 DB "Resta var-fn = -cte no anda Double", 0
cadena@@348 DB "Resta fn-var = -cte anda Double", 0
cadena@@349 DB "Resta fn-var = -cte no anda Double", 0
cadena@@350 DB "Resta -cte-fn = -cte anda Double", 0
cadena@@351 DB "Resta -cte-fn = -cte no anda Double", 0
cadena@@352 DB "Resta fn-cte = -cte anda Double", 0
cadena@@353 DB "Resta fn-cte = -cte no anda Double", 0
cadena@@354 DB "Resta fn-fn = -cte anda Double", 0
cadena@@355 DB "Resta fn-fn = -cte no anda Double", 0
cadena@@356 DB "Resta var-fn = var anda Double", 0
cadena@@357 DB "Resta var-fn = var no anda Double", 0
cadena@@358 DB "Resta -cte-cte = fn anda Double", 0
cadena@@359 DB "Resta -cte-cte = fn no anda Double", 0
const@@_3_0 DQ -3.0
const@@_10_0 DQ -10.0
const@@_13_0 DQ -13.0
const@@_7_0 DQ -7.0
aux_mem_2bytes DW ?
const@@3_0 DQ 3.0
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
FLD const@@_3_0
FSTP b@Variable@main
FLD a@Variable@main
FLD b@Variable@main
FSUB
FSTP var@@aux2
FLD var@@aux2
FSTP c@Variable@main
FLD c@Variable@main
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr cadena@@336, addr cadena@@336, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@337, addr cadena@@337, MB_OK
Label9:
FLD const@@_10_0
FSTP a@Variable@main
FLD const@@_3_0
FSTP b@Variable@main
FLD a@Variable@main
FLD b@Variable@main
FSUB
FSTP var@@aux11
FLD var@@aux11
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label16
invoke MessageBox, NULL, addr cadena@@338, addr cadena@@338, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@339, addr cadena@@339, MB_OK
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
invoke MessageBox, NULL, addr cadena@@340, addr cadena@@340, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@341, addr cadena@@341, MB_OK
Label24:
FLD const@@_3_0
FSTP b@Variable@main
FLD const@@_10_0
FLD b@Variable@main
FSUB
FSTP var@@aux25
FLD var@@aux25
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label30
invoke MessageBox, NULL, addr cadena@@342, addr cadena@@342, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@343, addr cadena@@343, MB_OK
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
invoke MessageBox, NULL, addr cadena@@344, addr cadena@@344, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@345, addr cadena@@345, MB_OK
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
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label48
invoke MessageBox, NULL, addr cadena@@346, addr cadena@@346, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@347, addr cadena@@347, MB_OK
Label49:
FLD const@@_3_0
FSTP b@Variable@main
CALL salida10
FLD salida10@Funcion@main

FLD b@Variable@main
FSUB
FSTP var@@aux51
FLD var@@aux51
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label56
invoke MessageBox, NULL, addr cadena@@348, addr cadena@@348, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@349, addr cadena@@349, MB_OK
Label57:
CALL salida3
FLD const@@_10_0
FLD salida3@Funcion@main
FSUB
FSTP var@@aux58
FLD var@@aux58
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label63
invoke MessageBox, NULL, addr cadena@@350, addr cadena@@350, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@351, addr cadena@@351, MB_OK
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
invoke MessageBox, NULL, addr cadena@@352, addr cadena@@352, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@353, addr cadena@@353, MB_OK
Label71:
CALL salida10
CALL salida3
FLD salida10@Funcion@main

FLD salida3@Funcion@main
FSUB
FSTP var@@aux73
FLD var@@aux73
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label78
invoke MessageBox, NULL, addr cadena@@354, addr cadena@@354, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@355, addr cadena@@355, MB_OK
Label79:
FLD const@@_10_0
FSTP a@Variable@main
FLD const@@_7_0
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
invoke MessageBox, NULL, addr cadena@@356, addr cadena@@356, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@357, addr cadena@@357, MB_OK
Label88:
FLD const@@_10_0
FLD const@@3_0
FSUB
FSTP var@@aux90
CALL salida13
FLD var@@aux90
FLD salida13@Funcion@main
FCOM
FSTSW AX
SAHF
JNE Label96
invoke MessageBox, NULL, addr cadena@@358, addr cadena@@358, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@359, addr cadena@@359, MB_OK
Label97:
invoke ExitProcess, 0
end start