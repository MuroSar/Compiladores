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
cadena@@510 DB "Suma var = cte anda Double", 0
cadena@@511 DB "Suma var = cte no anda Double", 0
cadena@@512 DB "Suma var+var = cte anda Double", 0
cadena@@513 DB "Suma var+var = cte no anda Double", 0
cadena@@514 DB "Suma var+cte = cte anda Double", 0
cadena@@515 DB "Suma var+cte = cte no anda Double", 0
cadena@@516 DB "Suma cte+var = cte anda Double", 0
cadena@@517 DB "Suma cte+var = cte no anda Double", 0
cadena@@518 DB "Suma cte+cte = cte anda Double", 0
cadena@@519 DB "Suma cte+cte = cte no anda Double", 0
cadena@@520 DB "Suma var+fn = cte anda Double", 0
cadena@@521 DB "Suma var+fn = cte no anda Double", 0
cadena@@522 DB "Suma fn+var = cte anda Double", 0
cadena@@523 DB "Suma fn+var = cte no anda Double", 0
cadena@@524 DB "Suma cte+fn = cte anda Double", 0
cadena@@525 DB "Suma cte+fn = cte no anda Double", 0
cadena@@526 DB "Suma fn+cte = cte anda Double", 0
cadena@@527 DB "Suma fn+cte = cte no anda Double", 0
cadena@@528 DB "Suma fn+fn = cte anda Double", 0
cadena@@529 DB "Suma fn+fn = cte no anda Double", 0
cadena@@530 DB "Suma var+fn = var anda Double", 0
cadena@@531 DB "Suma var+fn = var no anda Double", 0
cadena@@532 DB "Suma cte+cte = fn anda Double", 0
cadena@@533 DB "Suma cte+cte = fn no anda Double", 0
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
salida13 proc
FLD const@@13_0
FSTP ab1@Variable@main@salida13
FLD ab1@Variable@main@salida13
FSTP salida13@Funcion@main
RET
salida13 endp
start:
FLD const@@10_0
FSTP a@Variable@main
FLD const@@3_0
FSTP b@Variable@main
FLD a@Variable@main
FLD b@Variable@main
FADD
FSTP var@@aux2
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
FSTP c@Variable@main
FLD c@Variable@main
FLD const@@13_0
FCOM
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr cadena@@510, addr cadena@@510, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@511, addr cadena@@511, MB_OK
Label9:
FLD const@@10_0
FSTP a@Variable@main
FLD const@@3_0
FSTP b@Variable@main
FLD a@Variable@main
FLD b@Variable@main
FADD
FSTP var@@aux11
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
invoke MessageBox, NULL, addr cadena@@512, addr cadena@@512, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@513, addr cadena@@513, MB_OK
Label17:
FLD const@@10_0
FSTP a@Variable@main
FLD a@Variable@main
FLD const@@3_0
FADD
FSTP var@@aux18
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
invoke MessageBox, NULL, addr cadena@@514, addr cadena@@514, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@515, addr cadena@@515, MB_OK
Label24:
FLD const@@3_0
FSTP b@Variable@main
FLD const@@10_0
FLD b@Variable@main
FADD
FSTP var@@aux25
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
invoke MessageBox, NULL, addr cadena@@516, addr cadena@@516, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@517, addr cadena@@517, MB_OK
Label31:
FLD const@@10_0
FLD const@@3_0
FADD
FSTP var@@aux31
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
invoke MessageBox, NULL, addr cadena@@518, addr cadena@@518, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@519, addr cadena@@519, MB_OK
Label37:
Label39:
FLD const@@10_0
FSTP a@Variable@main
CALL salida3
FLD a@Variable@main
FLD salida3@Funcion@main
FADD
FSTP var@@aux43
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
invoke MessageBox, NULL, addr cadena@@520, addr cadena@@520, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@521, addr cadena@@521, MB_OK
Label49:
FLD const@@3_0
FSTP b@Variable@main
CALL salida10
FLD salida10@Funcion@main
FLD b@Variable@main
FADD
FSTP var@@aux51
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
invoke MessageBox, NULL, addr cadena@@522, addr cadena@@522, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@523, addr cadena@@523, MB_OK
Label57:
CALL salida3
FLD const@@10_0
FLD salida3@Funcion@main
FADD
FSTP var@@aux58
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
invoke MessageBox, NULL, addr cadena@@524, addr cadena@@524, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@525, addr cadena@@525, MB_OK
Label64:
CALL salida10
FLD salida10@Funcion@main
FLD const@@3_0
FADD
FSTP var@@aux65
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
invoke MessageBox, NULL, addr cadena@@526, addr cadena@@526, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@527, addr cadena@@527, MB_OK
Label71:
CALL salida10
CALL salida3
FLD salida10@Funcion@main
FLD salida3@Funcion@main
FADD
FSTP var@@aux73
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
invoke MessageBox, NULL, addr cadena@@528, addr cadena@@528, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@529, addr cadena@@529, MB_OK
Label79:
FLD const@@10_0
FSTP a@Variable@main
FLD const@@13_0
FSTP c@Variable@main
CALL salida3
FLD a@Variable@main
FLD salida3@Funcion@main
FADD
FSTP var@@aux82
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
FLD c@Variable@main
FCOM
FSTSW AX
SAHF
JNE Label87
invoke MessageBox, NULL, addr cadena@@530, addr cadena@@530, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@531, addr cadena@@531, MB_OK
Label88:
FLD const@@10_0
FLD const@@3_0
FADD
FSTP var@@aux90
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
FLD salida13@Funcion@main
FCOM
FSTSW AX
SAHF
JNE Label96
invoke MessageBox, NULL, addr cadena@@532, addr cadena@@532, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@533, addr cadena@@533, MB_OK
Label97:
invoke ExitProcess, 0
end start