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
cadena@@174 DB "Resta var = -cte anda Double", 0
cadena@@175 DB "Resta var = -cte no anda Double", 0
cadena@@176 DB "Resta var-var = -cte anda Double", 0
cadena@@177 DB "Resta var-var = -cte no anda Double", 0
cadena@@178 DB "Resta var-cte = -cte anda Double", 0
cadena@@179 DB "Resta var-cte = -cte no anda Double", 0
cadena@@180 DB "Resta -cte-var = -cte anda Double", 0
cadena@@181 DB "Resta -cte-var = -cte no anda Double", 0
cadena@@182 DB "Resta -cte-cte = -cte anda Double", 0
cadena@@183 DB "Resta -cte-cte = -cte no anda Double", 0
cadena@@184 DB "Resta var-fn = -cte anda Double", 0
cadena@@185 DB "Resta var-fn = -cte no anda Double", 0
cadena@@186 DB "Resta fn-var = -cte anda Double", 0
cadena@@187 DB "Resta fn-var = -cte no anda Double", 0
cadena@@188 DB "Resta -cte-fn = -cte anda Double", 0
cadena@@189 DB "Resta -cte-fn = -cte no anda Double", 0
cadena@@190 DB "Resta fn-cte = -cte anda Double", 0
cadena@@191 DB "Resta fn-cte = -cte no anda Double", 0
cadena@@192 DB "Resta fn-fn = -cte anda Double", 0
cadena@@193 DB "Resta fn-fn = -cte no anda Double", 0
cadena@@194 DB "Resta var-fn = var anda Double", 0
cadena@@195 DB "Resta var-fn = var no anda Double", 0
cadena@@196 DB "Resta -cte-cte = fn anda Double", 0
cadena@@197 DB "Resta -cte-cte = fn no anda Double", 0
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
FLD const@@_3_0
FST b@Variable
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD var@@aux2
FST c@Variable
FLD c@Variable
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr cadena@@174, addr cadena@@174, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@175, addr cadena@@175, MB_OK
Label9:
FLD const@@_10_0
FST a@Variable
FLD const@@_3_0
FST b@Variable
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux11
FLD var@@aux11
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label16
invoke MessageBox, NULL, addr cadena@@176, addr cadena@@176, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@177, addr cadena@@177, MB_OK
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
invoke MessageBox, NULL, addr cadena@@178, addr cadena@@178, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@179, addr cadena@@179, MB_OK
Label24:
FLD const@@_3_0
FST b@Variable
FLD const@@_10_0
FLD b@Variable
FSUB
FST var@@aux25
FLD var@@aux25
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label30
invoke MessageBox, NULL, addr cadena@@180, addr cadena@@180, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@181, addr cadena@@181, MB_OK
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
invoke MessageBox, NULL, addr cadena@@182, addr cadena@@182, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@183, addr cadena@@183, MB_OK
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
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label48
invoke MessageBox, NULL, addr cadena@@184, addr cadena@@184, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@185, addr cadena@@185, MB_OK
Label49:
FLD const@@_3_0
FST b@Variable
CALL salida10
FLD salida10@Funcion

FLD b@Variable
FSUB
FST var@@aux51
FLD var@@aux51
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label56
invoke MessageBox, NULL, addr cadena@@186, addr cadena@@186, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@187, addr cadena@@187, MB_OK
Label57:
CALL salida3
FLD const@@_10_0
FLD salida3@Funcion
FSUB
FST var@@aux58
FLD var@@aux58
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label63
invoke MessageBox, NULL, addr cadena@@188, addr cadena@@188, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@189, addr cadena@@189, MB_OK
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
invoke MessageBox, NULL, addr cadena@@190, addr cadena@@190, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@191, addr cadena@@191, MB_OK
Label71:
CALL salida10
CALL salida3
FLD salida10@Funcion

FLD salida3@Funcion
FSUB
FST var@@aux73
FLD var@@aux73
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label78
invoke MessageBox, NULL, addr cadena@@192, addr cadena@@192, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@193, addr cadena@@193, MB_OK
Label79:
FLD const@@_10_0
FST a@Variable
FLD const@@_7_0
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
invoke MessageBox, NULL, addr cadena@@194, addr cadena@@194, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@195, addr cadena@@195, MB_OK
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
invoke MessageBox, NULL, addr cadena@@196, addr cadena@@196, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@197, addr cadena@@197, MB_OK
Label97:
invoke ExitProcess, 0
end start