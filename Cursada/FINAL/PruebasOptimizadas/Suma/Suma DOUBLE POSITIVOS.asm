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
cadena@@350 DB "Suma var = cte anda Double", 0
cadena@@351 DB "Suma var = cte no anda Double", 0
cadena@@352 DB "Suma var+var = cte anda Double", 0
cadena@@353 DB "Suma var+var = cte no anda Double", 0
cadena@@354 DB "Suma var+cte = cte anda Double", 0
cadena@@355 DB "Suma var+cte = cte no anda Double", 0
cadena@@356 DB "Suma cte+var = cte anda Double", 0
cadena@@357 DB "Suma cte+var = cte no anda Double", 0
cadena@@358 DB "Suma cte+cte = cte anda Double", 0
cadena@@359 DB "Suma cte+cte = cte no anda Double", 0
cadena@@360 DB "Suma var+fn = cte anda Double", 0
cadena@@361 DB "Suma var+fn = cte no anda Double", 0
cadena@@362 DB "Suma fn+var = cte anda Double", 0
cadena@@363 DB "Suma fn+var = cte no anda Double", 0
cadena@@364 DB "Suma cte+fn = cte anda Double", 0
cadena@@365 DB "Suma cte+fn = cte no anda Double", 0
cadena@@366 DB "Suma fn+cte = cte anda Double", 0
cadena@@367 DB "Suma fn+cte = cte no anda Double", 0
cadena@@368 DB "Suma fn+fn = cte anda Double", 0
cadena@@369 DB "Suma fn+fn = cte no anda Double", 0
cadena@@370 DB "Suma var+fn = var anda Double", 0
cadena@@371 DB "Suma var+fn = var no anda Double", 0
cadena@@372 DB "Suma cte+cte = fn anda Double", 0
cadena@@373 DB "Suma cte+cte = fn no anda Double", 0
const@@3_0 DQ 3.0
const@@10_0 DQ 10.0
const@@13_0 DQ 13.0
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
salida13 proc
FLD const@@13_0
FST ab1@Variable
FLD ab1@Variable
FSTP salida13@Funcion
RET
salida13 endp
start:
FLD const@@10_0
FST a@Variable
FLD const@@3_0
FST b@Variable
FLD a@Variable
FLD b@Variable
FADD
FST var@@aux2
FLD var@@aux2
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@2
FLD var@@aux2
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux2
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@2:
FLD var@@aux2
FST c@Variable
FLD c@Variable
FLD const@@13_0
FCOM
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr cadena@@350, addr cadena@@350, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@351, addr cadena@@351, MB_OK
Label9:
FLD const@@10_0
FST a@Variable
FLD const@@3_0
FST b@Variable
FLD a@Variable
FLD b@Variable
FADD
FST var@@aux11
FLD var@@aux11
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@11
FLD var@@aux11
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux11
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@11:
FLD var@@aux11
FLD const@@13_0
FCOM
FSTSW AX
SAHF
JNE Label16
invoke MessageBox, NULL, addr cadena@@352, addr cadena@@352, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@353, addr cadena@@353, MB_OK
Label17:
FLD const@@10_0
FST a@Variable
FLD a@Variable
FLD const@@3_0
FADD
FST var@@aux18
FLD var@@aux18
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@18
FLD var@@aux18
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux18
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@18:
FLD var@@aux18
FLD const@@13_0
FCOM
FSTSW AX
SAHF
JNE Label23
invoke MessageBox, NULL, addr cadena@@354, addr cadena@@354, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@355, addr cadena@@355, MB_OK
Label24:
FLD const@@3_0
FST b@Variable
FLD const@@10_0
FLD b@Variable
FADD
FST var@@aux25
FLD var@@aux25
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@25
FLD var@@aux25
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux25
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@25:
FLD var@@aux25
FLD const@@13_0
FCOM
FSTSW AX
SAHF
JNE Label30
invoke MessageBox, NULL, addr cadena@@356, addr cadena@@356, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@357, addr cadena@@357, MB_OK
Label31:
FLD const@@10_0
FLD const@@3_0
FADD
FST var@@aux31
FLD var@@aux31
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@31
FLD var@@aux31
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux31
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@31:
FLD var@@aux31
FLD const@@13_0
FCOM
FSTSW AX
SAHF
JNE Label36
invoke MessageBox, NULL, addr cadena@@358, addr cadena@@358, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@359, addr cadena@@359, MB_OK
Label37:
Label39:
FLD const@@10_0
FST a@Variable
CALL salida3
FLD a@Variable
FLD salida3@Funcion
FADD
FST var@@aux43
FLD var@@aux43
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@43
FLD var@@aux43
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux43
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@43:
FLD var@@aux43
FLD const@@13_0
FCOM
FSTSW AX
SAHF
JNE Label48
invoke MessageBox, NULL, addr cadena@@360, addr cadena@@360, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@361, addr cadena@@361, MB_OK
Label49:
FLD const@@3_0
FST b@Variable
CALL salida10
FLD salida10@Funcion
FLD b@Variable
FADD
FST var@@aux51
FLD var@@aux51
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@51
FLD var@@aux51
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux51
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@51:
FLD var@@aux51
FLD const@@13_0
FCOM
FSTSW AX
SAHF
JNE Label56
invoke MessageBox, NULL, addr cadena@@362, addr cadena@@362, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@363, addr cadena@@363, MB_OK
Label57:
CALL salida3
FLD const@@10_0
FLD salida3@Funcion
FADD
FST var@@aux58
FLD var@@aux58
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@58
FLD var@@aux58
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux58
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@58:
FLD var@@aux58
FLD const@@13_0
FCOM
FSTSW AX
SAHF
JNE Label63
invoke MessageBox, NULL, addr cadena@@364, addr cadena@@364, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@365, addr cadena@@365, MB_OK
Label64:
CALL salida10
FLD salida10@Funcion
FLD const@@3_0
FADD
FST var@@aux65
FLD var@@aux65
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@65
FLD var@@aux65
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux65
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@65:
FLD var@@aux65
FLD const@@13_0
FCOM
FSTSW AX
SAHF
JNE Label70
invoke MessageBox, NULL, addr cadena@@366, addr cadena@@366, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@367, addr cadena@@367, MB_OK
Label71:
CALL salida10
CALL salida3
FLD salida10@Funcion
FLD salida3@Funcion
FADD
FST var@@aux73
FLD var@@aux73
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@73
FLD var@@aux73
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux73
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@73:
FLD var@@aux73
FLD const@@13_0
FCOM
FSTSW AX
SAHF
JNE Label78
invoke MessageBox, NULL, addr cadena@@368, addr cadena@@368, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@369, addr cadena@@369, MB_OK
Label79:
FLD const@@10_0
FST a@Variable
FLD const@@13_0
FST c@Variable
CALL salida3
FLD a@Variable
FLD salida3@Funcion
FADD
FST var@@aux82
FLD var@@aux82
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@82
FLD var@@aux82
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux82
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@82:
FLD var@@aux82
FLD c@Variable
FCOM
FSTSW AX
SAHF
JNE Label87
invoke MessageBox, NULL, addr cadena@@370, addr cadena@@370, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@371, addr cadena@@371, MB_OK
Label88:
FLD const@@10_0
FLD const@@3_0
FADD
FST var@@aux90
FLD var@@aux90
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@90
FLD var@@aux90
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux90
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@90:
CALL salida13
FLD salida13@Funcion
FCOM
FSTSW AX
SAHF
JNE Label96
invoke MessageBox, NULL, addr cadena@@372, addr cadena@@372, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@373, addr cadena@@373, MB_OK
Label97:
invoke ExitProcess, 0
end start