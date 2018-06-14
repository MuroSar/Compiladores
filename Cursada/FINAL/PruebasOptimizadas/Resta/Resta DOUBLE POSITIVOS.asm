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
salida10@Funcion DQ ?
var@@aux58 DQ ?
var@@aux18 DQ ?
var@@aux2 DQ ?
var@@aux65 DQ ?
b@Variable DQ ?
salida3@Funcion DQ ?
salida7@Funcion DQ ?
c@Variable DQ ?
var@@aux51 DQ ?
var@@aux38 DQ ?
ab1@Variable DQ ?
a@Variable DQ ?
var@@aux82 DQ ?
var@@aux40 DQ ?
var@@aux43 DQ ?
var@@aux89 DQ ?
var@@aux25 DQ ?
var@@aux73 DQ ?
var@@aux31 DQ ?
cadena@@330 DB "Resta var = cte anda Double", 0
cadena@@331 DB "Resta var = cte no anda Double", 0
cadena@@332 DB "Resta var-var = cte anda Double", 0
cadena@@333 DB "Resta var-var = cte no anda Double", 0
cadena@@334 DB "Resta var-cte = cte anda Double", 0
cadena@@335 DB "Resta var-cte = cte no anda Double", 0
cadena@@336 DB "Resta cte-var = cte anda Double", 0
cadena@@337 DB "Resta cte-var = cte no anda Double", 0
cadena@@338 DB "Resta cte-cte = cte anda Double", 0
cadena@@339 DB "Resta cte-cte = cte no anda Double", 0
cadena@@340 DB "Resta var-fn = cte anda Double", 0
cadena@@341 DB "Resta var-fn = cte no anda Double", 0
cadena@@342 DB "Resta fn-var = cte anda Double", 0
cadena@@343 DB "Resta fn-var = cte no anda Double", 0
cadena@@344 DB "Resta cte-fn = cte anda Double", 0
cadena@@345 DB "Resta cte-fn = cte no anda Double", 0
cadena@@346 DB "Resta fn-cte = cte anda Double", 0
cadena@@347 DB "Resta fn-cte = cte no anda Double", 0
cadena@@348 DB "Resta fn-fn = cte anda Double", 0
cadena@@349 DB "Resta fn-fn = cte no anda Double", 0
cadena@@350 DB "Resta var-fn = var anda Double", 0
cadena@@351 DB "Resta var-fn = var no anda Double", 0
cadena@@352 DB "Resta cte-cte = fn anda Double", 0
cadena@@353 DB "Resta cte-cte = fn no anda Double", 0
const@@3_0 DQ 3.0
const@@10_0 DQ 10.0
const@@7_0 DQ 7.0
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
FST ab1@Variable
FLD ab1@Variable
FSTP salida3@Funcion
RET
salida3 endp
salida10 proc
FLD const@@10_0
FST ab1@Variable
FLD ab1@Variable
FSTP salida10@Funcion
RET
salida10 endp
salida7 proc
FLD const@@7_0
FST ab1@Variable
FLD ab1@Variable
FSTP salida7@Funcion
RET
salida7 endp
start:
FLD const@@10_0
FST a@Variable
FLD const@@3_0
FST b@Variable
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD var@@aux2
FST c@Variable
FLD c@Variable
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr cadena@@330, addr cadena@@330, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@331, addr cadena@@331, MB_OK
Label9:
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
Label9:
FLD const@@10_0
FST a@Variable
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD const@@3_0
FST b@Variable
FLD var@@aux11
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label16
invoke MessageBox, NULL, addr cadena@@332, addr cadena@@332, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@333, addr cadena@@333, MB_OK
Label17:
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
Label17:
FLD const@@10_0
FST a@Variable
FLD var@@aux18
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label23
invoke MessageBox, NULL, addr cadena@@334, addr cadena@@334, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@335, addr cadena@@335, MB_OK
Label24:
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD a@Variable
FLD const@@3_0
FSUB
FST var@@aux18
Label24:
FLD const@@3_0
FST b@Variable
FLD var@@aux25
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label30
invoke MessageBox, NULL, addr cadena@@336, addr cadena@@336, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@337, addr cadena@@337, MB_OK
Label31:
FLD var@@aux31
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label36
invoke MessageBox, NULL, addr cadena@@338, addr cadena@@338, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@339, addr cadena@@339, MB_OK
Label37:
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD a@Variable
FLD const@@3_0
FSUB
FST var@@aux18
FLD const@@10_0
FLD b@Variable
FSUB
FST var@@aux25
Label31:
FLD const@@10_0
FLD const@@3_0
FSUB
FST var@@aux31
Label37:
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD a@Variable
FLD const@@3_0
FSUB
FST var@@aux18
FLD const@@10_0
FLD b@Variable
FSUB
FST var@@aux25
Label31:
FLD const@@10_0
FLD const@@3_0
FSUB
FST var@@aux31
Label39:
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD a@Variable
FLD const@@3_0
FSUB
FST var@@aux18
FLD const@@10_0
FLD b@Variable
FSUB
FST var@@aux25
Label31:
FLD const@@10_0
FLD const@@3_0
FSUB
FST var@@aux31
FLD const@@10_0
FST a@Variable
CALL salida3
FLD var@@aux43
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label48
invoke MessageBox, NULL, addr cadena@@340, addr cadena@@340, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@341, addr cadena@@341, MB_OK
Label49:
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD a@Variable
FLD const@@3_0
FSUB
FST var@@aux18
FLD const@@10_0
FLD b@Variable
FSUB
FST var@@aux25
Label31:
FLD const@@10_0
FLD const@@3_0
FSUB
FST var@@aux31
FLD a@Variable
FLD salida3@Funcion
FSUB
FST var@@aux43
Label49:
FLD const@@3_0
FST b@Variable
CALL salida10
FLD var@@aux51
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label56
invoke MessageBox, NULL, addr cadena@@342, addr cadena@@342, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@343, addr cadena@@343, MB_OK
Label57:
CALL salida3
FLD var@@aux58
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label63
invoke MessageBox, NULL, addr cadena@@344, addr cadena@@344, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@345, addr cadena@@345, MB_OK
Label64:
CALL salida10
FLD var@@aux65
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label70
invoke MessageBox, NULL, addr cadena@@346, addr cadena@@346, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@347, addr cadena@@347, MB_OK
Label71:
CALL salida10
CALL salida3
FLD var@@aux73
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label78
invoke MessageBox, NULL, addr cadena@@348, addr cadena@@348, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@349, addr cadena@@349, MB_OK
Label79:
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD a@Variable
FLD const@@3_0
FSUB
FST var@@aux18
FLD const@@10_0
FLD b@Variable
FSUB
FST var@@aux25
Label31:
FLD const@@10_0
FLD const@@3_0
FSUB
FST var@@aux31
FLD a@Variable
FLD salida3@Funcion
FSUB
FST var@@aux43
FLD salida10@Funcion

FLD b@Variable
FSUB
FST var@@aux51
FLD const@@10_0
FLD salida3@Funcion
FSUB
FST var@@aux58
FLD salida10@Funcion

FLD const@@3_0
FSUB
FST var@@aux65
FLD salida10@Funcion

FLD salida3@Funcion
FSUB
FST var@@aux73
Label79:
FLD const@@10_0
FST a@Variable
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD a@Variable
FLD const@@3_0
FSUB
FST var@@aux18
FLD const@@10_0
FLD b@Variable
FSUB
FST var@@aux25
Label31:
FLD const@@10_0
FLD const@@3_0
FSUB
FST var@@aux31
FLD a@Variable
FLD salida3@Funcion
FSUB
FST var@@aux43
FLD salida10@Funcion

FLD b@Variable
FSUB
FST var@@aux51
FLD const@@10_0
FLD salida3@Funcion
FSUB
FST var@@aux58
FLD salida10@Funcion

FLD const@@3_0
FSUB
FST var@@aux65
FLD salida10@Funcion

FLD salida3@Funcion
FSUB
FST var@@aux73
FLD const@@7_0
FST c@Variable
CALL salida3
FLD var@@aux82
FLD c@Variable
FCOM
FSTSW AX
SAHF
JNE Label87
invoke MessageBox, NULL, addr cadena@@350, addr cadena@@350, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@351, addr cadena@@351, MB_OK
Label88:
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD a@Variable
FLD const@@3_0
FSUB
FST var@@aux18
FLD const@@10_0
FLD b@Variable
FSUB
FST var@@aux25
Label31:
FLD const@@10_0
FLD const@@3_0
FSUB
FST var@@aux31
FLD a@Variable
FLD salida3@Funcion
FSUB
FST var@@aux43
FLD salida10@Funcion

FLD b@Variable
FSUB
FST var@@aux51
FLD const@@10_0
FLD salida3@Funcion
FSUB
FST var@@aux58
FLD salida10@Funcion

FLD const@@3_0
FSUB
FST var@@aux65
FLD salida10@Funcion

FLD salida3@Funcion
FSUB
FST var@@aux73
FLD a@Variable
FLD salida3@Funcion
FSUB
FST var@@aux82
Label88:
CALL salida7
FLD salida7@Funcion
FCOM
FSTSW AX
SAHF
JNE Label96
invoke MessageBox, NULL, addr cadena@@352, addr cadena@@352, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@353, addr cadena@@353, MB_OK
Label97:
invoke ExitProcess, 0
end start