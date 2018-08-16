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
var@@aux58 DD ?
var@@aux18 DD ?
a@Variable@main DD ?
var@@aux2 DD ?
var@@aux65 DD ?
c@Variable@main DD ?
var@@aux51 DD ?
var@@aux97 DD ?
var@@aux11 DD ?
salida10@Funcion@main DD ?
var@@aux38 DD ?
ab1@Variable@main@salida5 DD ?
ab1@Variable@main@salida10 DD ?
var@@aux82 DD ?
var@@aux40 DD ?
var@@aux88 DD ?
var@@aux43 DD ?
var@@aux25 DD ?
b@Variable@main DD ?
salida5@Funcion@main DD ?
var@@aux73 DD ?
var@@aux31 DD ?
cadena@@104 DB "Division var = cte anda Long", 0
cadena@@105 DB "Division var = cte no anda Long", 0
cadena@@106 DB "Division var/var = cte anda Long", 0
cadena@@107 DB "Division var/var = cte no anda Long", 0
cadena@@108 DB "Division var/cte = cte anda Long", 0
cadena@@109 DB "Division var/cte = cte no anda Long", 0
cadena@@110 DB "Division cte/var = cte anda Long", 0
cadena@@111 DB "Division cte/var = cte no anda Long", 0
cadena@@112 DB "Division cte/cte = cte anda Long", 0
cadena@@113 DB "Division cte/cte = cte no anda Long", 0
cadena@@114 DB "Division var/fn = cte anda Long", 0
cadena@@115 DB "Division var/fn = cte no anda Long", 0
cadena@@116 DB "Division fn/var = cte anda Long", 0
cadena@@117 DB "Division fn/var = cte no anda Long", 0
cadena@@118 DB "Division cte/fn = cte anda Long", 0
cadena@@119 DB "Division cte/fn = cte no anda Long", 0
cadena@@120 DB "Division fn/cte = cte anda Long", 0
cadena@@121 DB "Division fn/cte = cte no anda Long", 0
cadena@@122 DB "Division fn/fn = cte anda Long", 0
cadena@@123 DB "Division fn/fn = cte no anda Long", 0
cadena@@124 DB "Division var/fn = var anda Long", 0
cadena@@125 DB "Division var/fn = var no anda Long", 0
cadena@@126 DB "Division cte/cte = fn anda Long", 0
cadena@@127 DB "Division cte/cte = fn no anda Long", 0
cadena@@128 DB "Division por cero no anda Long", 0
cadena@@129 DB "Division no anda Long", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida5 proc
MOV ab1@Variable@main@salida5,5
MOV EAX,ab1@Variable@main@salida5
MOV salida5@Funcion@main,EAX
RET
salida5 endp
salida10 proc
MOV ab1@Variable@main@salida10,10
MOV EAX,ab1@Variable@main@salida10
MOV salida10@Funcion@main,EAX
RET
salida10 endp
start:
MOV a@Variable@main,10
MOV b@Variable@main,5
MOV EAX,a@Variable@main
CDQ
CMP b@Variable@main,0
JE _division_cero
MOV EBX,b@Variable@main
CDQ
IDIV EBX
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV c@Variable@main,EAX
MOV EAX,c@Variable@main
MOV EDX,2
CMP c@Variable@main,EDX
JNE Label8
invoke MessageBox, NULL, addr cadena@@104, addr cadena@@104, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@105, addr cadena@@105, MB_OK
Label9:
MOV a@Variable@main,10
MOV b@Variable@main,5
MOV EAX,a@Variable@main
CDQ
CMP b@Variable@main,0
JE _division_cero
MOV EBX,b@Variable@main
CDQ
IDIV EBX
MOV var@@aux11,EAX
MOV EAX,var@@aux11
MOV EDX,2
CMP var@@aux11,EDX
JNE Label16
invoke MessageBox, NULL, addr cadena@@106, addr cadena@@106, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@107, addr cadena@@107, MB_OK
Label17:
MOV a@Variable@main,10
MOV EAX,a@Variable@main
CDQ
CMP ECX,0
JE _division_cero
MOV ECX,5
MOV EBX,5
CDQ
IDIV EBX
MOV var@@aux18,EAX
MOV EAX,var@@aux18
MOV EDX,2
CMP var@@aux18,EDX
JNE Label23
invoke MessageBox, NULL, addr cadena@@108, addr cadena@@108, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@109, addr cadena@@109, MB_OK
Label24:
MOV b@Variable@main,5
MOV EAX,10
CDQ
CMP b@Variable@main,0
JE _division_cero
MOV EBX,b@Variable@main
CDQ
IDIV EBX
MOV var@@aux25,EAX
MOV EAX,var@@aux25
MOV EDX,2
CMP var@@aux25,EDX
JNE Label30
invoke MessageBox, NULL, addr cadena@@110, addr cadena@@110, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@111, addr cadena@@111, MB_OK
Label31:
MOV EAX,10
CDQ
CMP ECX,0
JE _division_cero
MOV ECX,5
MOV EBX,5
CDQ
IDIV EBX
MOV var@@aux31,EAX
MOV EAX,var@@aux31
MOV EDX,2
CMP var@@aux31,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@112, addr cadena@@112, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@113, addr cadena@@113, MB_OK
Label37:
Label39:
MOV a@Variable@main,10
CALL salida5
MOV EAX,a@Variable@main
CDQ
CMP salida5@Funcion@main,0
JE _division_cero
MOV EBX, salida5@Funcion@main
MOV EBX,salida5@Funcion@main
CDQ
IDIV EBX
MOV var@@aux43,EAX
MOV EAX,var@@aux43
MOV EDX,2
CMP var@@aux43,EDX
JNE Label48
invoke MessageBox, NULL, addr cadena@@114, addr cadena@@114, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@115, addr cadena@@115, MB_OK
Label49:
MOV b@Variable@main,5
CALL salida10
MOV EAX,salida10@Funcion@main
CDQ
CMP b@Variable@main,0
JE _division_cero
MOV EBX,b@Variable@main
CDQ
IDIV EBX
MOV var@@aux51,EAX
MOV EAX,var@@aux51
MOV EDX,2
CMP var@@aux51,EDX
JNE Label56
invoke MessageBox, NULL, addr cadena@@116, addr cadena@@116, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@117, addr cadena@@117, MB_OK
Label57:
CALL salida5
MOV EAX,10
CDQ
CMP salida5@Funcion@main,0
JE _division_cero
MOV EBX, salida5@Funcion@main
MOV EBX,salida5@Funcion@main
CDQ
IDIV EBX
MOV var@@aux58,EAX
MOV EAX,var@@aux58
MOV EDX,2
CMP var@@aux58,EDX
JNE Label63
invoke MessageBox, NULL, addr cadena@@118, addr cadena@@118, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@119, addr cadena@@119, MB_OK
Label64:
CALL salida10
MOV EAX,salida10@Funcion@main
CDQ
CMP ECX,0
JE _division_cero
MOV ECX,5
MOV EBX,5
CDQ
IDIV EBX
MOV var@@aux65,EAX
MOV EAX,var@@aux65
MOV EDX,2
CMP var@@aux65,EDX
JNE Label70
invoke MessageBox, NULL, addr cadena@@120, addr cadena@@120, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@121, addr cadena@@121, MB_OK
Label71:
CALL salida10
CALL salida5
MOV EAX,salida10@Funcion@main
CDQ
CMP salida5@Funcion@main,0
JE _division_cero
MOV EBX, salida5@Funcion@main
MOV EBX,salida5@Funcion@main
CDQ
IDIV EBX
MOV var@@aux73,EAX
MOV EAX,var@@aux73
MOV EDX,2
CMP var@@aux73,EDX
JNE Label78
invoke MessageBox, NULL, addr cadena@@122, addr cadena@@122, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@123, addr cadena@@123, MB_OK
Label79:
MOV a@Variable@main,10
MOV c@Variable@main,2
CALL salida5
MOV EAX,a@Variable@main
CDQ
CMP salida5@Funcion@main,0
JE _division_cero
MOV EBX, salida5@Funcion@main
MOV EBX,salida5@Funcion@main
CDQ
IDIV EBX
MOV var@@aux82,EAX
MOV EAX,var@@aux82
MOV EAX,c@Variable@main
CMP var@@aux82,EAX
JNE Label87
invoke MessageBox, NULL, addr cadena@@124, addr cadena@@124, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@125, addr cadena@@125, MB_OK
Label88:
MOV EAX,10
CDQ
CMP ECX,0
JE _division_cero
MOV ECX,2
MOV EBX,2
CDQ
IDIV EBX
MOV var@@aux88,EAX
CALL salida5
MOV EAX,var@@aux88
CMP EAX,salida5@Funcion@main
JNE Label94
invoke MessageBox, NULL, addr cadena@@126, addr cadena@@126, MB_OK
JMP Label95
Label94:
invoke MessageBox, NULL, addr cadena@@127, addr cadena@@127, MB_OK
Label95:
MOV a@Variable@main,10
MOV b@Variable@main,0
MOV EAX,a@Variable@main
CDQ
CMP b@Variable@main,0
JE _division_cero
MOV EBX,b@Variable@main
CDQ
IDIV EBX
MOV var@@aux97,EAX
MOV EAX,var@@aux97
MOV c@Variable@main,EAX
MOV EAX,c@Variable@main
MOV EDX,2
CMP c@Variable@main,EDX
JNE Label103
invoke MessageBox, NULL, addr cadena@@128, addr cadena@@128, MB_OK
JMP Label104
Label103:
invoke MessageBox, NULL, addr cadena@@129, addr cadena@@129, MB_OK
Label104:
invoke ExitProcess, 0
end start