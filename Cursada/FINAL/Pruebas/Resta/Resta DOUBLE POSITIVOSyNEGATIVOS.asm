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
var@@aux90 DQ ?
c@Variable DQ ?
var@@aux51 DQ ?
var@@aux11 DQ ?
var@@aux38 DQ ?
ab1@Variable DQ ?
a@Variable DQ ?
salida13@Funcion DQ ?
var@@aux82 DQ ?
var@@aux40 DQ ?
var@@aux43 DQ ?
var@@aux89 DQ ?
var@@aux25 DQ ?
var@@aux73 DQ ?
var@@aux31 DQ ?
cadena@@398 DB "Resta var = -cte anda Double", 0
cadena@@399 DB "Resta var = -cte no anda Double", 0
cadena@@400 DB "Resta var-var = -cte anda Double", 0
cadena@@401 DB "Resta var-var = -cte no anda Double", 0
cadena@@402 DB "Resta var-cte = -cte anda Double", 0
cadena@@403 DB "Resta var-cte = -cte no anda Double", 0
cadena@@404 DB "Resta -cte-var = -cte anda Double", 0
cadena@@405 DB "Resta -cte-var = -cte no anda Double", 0
cadena@@406 DB "Resta -cte-cte = -cte anda Double", 0
cadena@@407 DB "Resta -cte-cte = -cte no anda Double", 0
cadena@@408 DB "Resta var-fn = -cte anda Double", 0
cadena@@409 DB "Resta var-fn = -cte no anda Double", 0
cadena@@410 DB "Resta fn-var = -cte anda Double", 0
cadena@@411 DB "Resta fn-var = -cte no anda Double", 0
cadena@@412 DB "Resta -cte-fn = -cte anda Double", 0
cadena@@413 DB "Resta -cte-fn = -cte no anda Double", 0
cadena@@414 DB "Resta fn-cte = -cte anda Double", 0
cadena@@415 DB "Resta fn-cte = -cte no anda Double", 0
cadena@@416 DB "Resta fn-fn = -cte anda Double", 0
cadena@@417 DB "Resta fn-fn = -cte no anda Double", 0
cadena@@418 DB "Resta var-fn = var anda Double", 0
cadena@@419 DB "Resta var-fn = var no anda Double", 0
cadena@@420 DB "Resta -cte-cte = fn anda Double", 0
cadena@@421 DB "Resta -cte-cte = fn no anda Double", 0
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
FST ab1@Variable
FLD ab1@Variable
FSTP salida3@Funcion
RET
salida3 endp
salida10 proc
FLD const@@_10_0
FST ab1@Variable
FLD ab1@Variable
FSTP salida10@Funcion
RET
salida10 endp
salida13 proc
FLD const@@_13_0
FST ab1@Variable
FLD ab1@Variable
FSTP salida13@Funcion
RET
salida13 endp
start:
FLD const@@_10_0
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
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr cadena@@398, addr cadena@@398, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@399, addr cadena@@399, MB_OK
Label9:
FLD const@@_10_0
FST a@Variable
FLD const@@3_0
FST b@Variable
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux11
FLD var@@aux11
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label16
invoke MessageBox, NULL, addr cadena@@400, addr cadena@@400, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@401, addr cadena@@401, MB_OK
Label17:
FLD const@@_10_0
FST a@Variable
FLD a@Variable
FLD const@@3_0
FSUB
FST var@@aux18
FLD var@@aux18
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label23
invoke MessageBox, NULL, addr cadena@@402, addr cadena@@402, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@403, addr cadena@@403, MB_OK
Label24:
FLD const@@3_0
FST b@Variable
FLD const@@_10_0
FLD b@Variable
FSUB
FST var@@aux25
FLD var@@aux25
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label30
invoke MessageBox, NULL, addr cadena@@404, addr cadena@@404, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@405, addr cadena@@405, MB_OK
Label31:
FLD const@@_10_0
FLD const@@3_0
FSUB
FST var@@aux31
FLD var@@aux31
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label36
invoke MessageBox, NULL, addr cadena@@406, addr cadena@@406, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@407, addr cadena@@407, MB_OK
Label37:
Label39:
FLD const@@_10_0
FST a@Variable
CALL salida3
FLD a@Variable
FLD salida3@Funcion
FSUB
FST var@@aux43
FLD var@@aux43
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label48
invoke MessageBox, NULL, addr cadena@@408, addr cadena@@408, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@409, addr cadena@@409, MB_OK
Label49:
FLD const@@3_0
FST b@Variable
CALL salida10
FLD salida10@Funcion

FLD b@Variable
FSUB
FST var@@aux51
FLD var@@aux51
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label56
invoke MessageBox, NULL, addr cadena@@410, addr cadena@@410, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@411, addr cadena@@411, MB_OK
Label57:
CALL salida3
FLD const@@_10_0
FLD salida3@Funcion
FSUB
FST var@@aux58
FLD var@@aux58
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label63
invoke MessageBox, NULL, addr cadena@@412, addr cadena@@412, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@413, addr cadena@@413, MB_OK
Label64:
CALL salida10
FLD salida10@Funcion

FLD const@@3_0
FSUB
FST var@@aux65
FLD var@@aux65
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label70
invoke MessageBox, NULL, addr cadena@@414, addr cadena@@414, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@415, addr cadena@@415, MB_OK
Label71:
CALL salida10
CALL salida3
FLD salida10@Funcion

FLD salida3@Funcion
FSUB
FST var@@aux73
FLD var@@aux73
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label78
invoke MessageBox, NULL, addr cadena@@416, addr cadena@@416, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@417, addr cadena@@417, MB_OK
Label79:
FLD const@@_10_0
FST a@Variable
FLD const@@_13_0
FST c@Variable
CALL salida3
FLD a@Variable
FLD salida3@Funcion
FSUB
FST var@@aux82
FLD var@@aux82
FLD c@Variable
FCOM
FSTSW AX
SAHF
JNE Label87
invoke MessageBox, NULL, addr cadena@@418, addr cadena@@418, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@419, addr cadena@@419, MB_OK
Label88:
FLD const@@_10_0
FLD const@@3_0
FSUB
FST var@@aux90
CALL salida13
FLD salida13@Funcion
FCOM
FSTSW AX
SAHF
JNE Label96
invoke MessageBox, NULL, addr cadena@@420, addr cadena@@420, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@421, addr cadena@@421, MB_OK
Label97:
invoke ExitProcess, 0
end start