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
var@@aux90 DQ ?
c@Variable DQ ?
var@@aux51 DQ ?
var@@aux11 DQ ?
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
cadena@@374 DB "Resta var = cte anda Double", 0
cadena@@375 DB "Resta var = cte no anda Double", 0
cadena@@376 DB "Resta var-var = cte anda Double", 0
cadena@@377 DB "Resta var-var = cte no anda Double", 0
cadena@@378 DB "Resta var-cte = cte anda Double", 0
cadena@@379 DB "Resta var-cte = cte no anda Double", 0
cadena@@380 DB "Resta cte-var = cte anda Double", 0
cadena@@381 DB "Resta cte-var = cte no anda Double", 0
cadena@@382 DB "Resta cte-cte = cte anda Double", 0
cadena@@383 DB "Resta cte-cte = cte no anda Double", 0
cadena@@384 DB "Resta var-fn = cte anda Double", 0
cadena@@385 DB "Resta var-fn = cte no anda Double", 0
cadena@@386 DB "Resta fn-var = cte anda Double", 0
cadena@@387 DB "Resta fn-var = cte no anda Double", 0
cadena@@388 DB "Resta cte-fn = cte anda Double", 0
cadena@@389 DB "Resta cte-fn = cte no anda Double", 0
cadena@@390 DB "Resta fn-cte = cte anda Double", 0
cadena@@391 DB "Resta fn-cte = cte no anda Double", 0
cadena@@392 DB "Resta fn-fn = cte anda Double", 0
cadena@@393 DB "Resta fn-fn = cte no anda Double", 0
cadena@@394 DB "Resta var-fn = var anda Double", 0
cadena@@395 DB "Resta var-fn = var no anda Double", 0
cadena@@396 DB "Resta cte-cte = fn anda Double", 0
cadena@@397 DB "Resta cte-cte = fn no anda Double", 0
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
invoke MessageBox, NULL, addr cadena@@374, addr cadena@@374, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@375, addr cadena@@375, MB_OK
Label9:
FLD const@@10_0
FST a@Variable
FLD const@@3_0
FST b@Variable
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux11
FLD var@@aux11
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label16
invoke MessageBox, NULL, addr cadena@@376, addr cadena@@376, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@377, addr cadena@@377, MB_OK
Label17:
FLD const@@10_0
FST a@Variable
FLD a@Variable
FLD const@@3_0
FSUB
FST var@@aux18
FLD var@@aux18
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label23
invoke MessageBox, NULL, addr cadena@@378, addr cadena@@378, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@379, addr cadena@@379, MB_OK
Label24:
FLD const@@3_0
FST b@Variable
FLD const@@10_0
FLD b@Variable
FSUB
FST var@@aux25
FLD var@@aux25
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label30
invoke MessageBox, NULL, addr cadena@@380, addr cadena@@380, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@381, addr cadena@@381, MB_OK
Label31:
FLD const@@10_0
FLD const@@3_0
FSUB
FST var@@aux31
FLD var@@aux31
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label36
invoke MessageBox, NULL, addr cadena@@382, addr cadena@@382, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@383, addr cadena@@383, MB_OK
Label37:
Label39:
FLD const@@10_0
FST a@Variable
CALL salida3
FLD a@Variable
FLD salida3@Funcion
FSUB
FST var@@aux43
FLD var@@aux43
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label48
invoke MessageBox, NULL, addr cadena@@384, addr cadena@@384, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@385, addr cadena@@385, MB_OK
Label49:
FLD const@@3_0
FST b@Variable
CALL salida10
FLD salida10@Funcion

FLD b@Variable
FSUB
FST var@@aux51
FLD var@@aux51
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label56
invoke MessageBox, NULL, addr cadena@@386, addr cadena@@386, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@387, addr cadena@@387, MB_OK
Label57:
CALL salida3
FLD const@@10_0
FLD salida3@Funcion
FSUB
FST var@@aux58
FLD var@@aux58
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label63
invoke MessageBox, NULL, addr cadena@@388, addr cadena@@388, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@389, addr cadena@@389, MB_OK
Label64:
CALL salida10
FLD salida10@Funcion

FLD const@@3_0
FSUB
FST var@@aux65
FLD var@@aux65
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label70
invoke MessageBox, NULL, addr cadena@@390, addr cadena@@390, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@391, addr cadena@@391, MB_OK
Label71:
CALL salida10
CALL salida3
FLD salida10@Funcion

FLD salida3@Funcion
FSUB
FST var@@aux73
FLD var@@aux73
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label78
invoke MessageBox, NULL, addr cadena@@392, addr cadena@@392, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@393, addr cadena@@393, MB_OK
Label79:
FLD const@@10_0
FST a@Variable
FLD const@@7_0
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
invoke MessageBox, NULL, addr cadena@@394, addr cadena@@394, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@395, addr cadena@@395, MB_OK
Label88:
FLD const@@10_0
FLD const@@3_0
FSUB
FST var@@aux90
CALL salida7
FLD salida7@Funcion
FCOM
FSTSW AX
SAHF
JNE Label96
invoke MessageBox, NULL, addr cadena@@396, addr cadena@@396, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@397, addr cadena@@397, MB_OK
Label97:
invoke ExitProcess, 0
end start