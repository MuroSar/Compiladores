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
salida10@Funcion DD ?
var@@aux58 DD ?
var@@aux18 DD ?
var@@aux2 DD ?
var@@aux65 DD ?
b@Variable DD ?
salida5@Funcion DD ?
c@Variable DD ?
var@@aux51 DD ?
var@@aux97 DD ?
var@@aux11 DD ?
var@@aux38 DD ?
ab1@Variable DD ?
a@Variable DD ?
var@@aux82 DD ?
var@@aux40 DD ?
var@@aux88 DD ?
var@@aux43 DD ?
var@@aux25 DD ?
var@@aux73 DD ?
var@@aux31 DD ?
cadena@@156 DB "Division var = -cte anda Long", 0
cadena@@157 DB "Division var = -cte no anda Long", 0
cadena@@158 DB "Division var/var = -cte anda Long", 0
cadena@@159 DB "Division var/var = -cte no anda Long", 0
cadena@@160 DB "Division var/-cte = -cte anda Long", 0
cadena@@161 DB "Division var/cte = -cte no anda Long", 0
cadena@@162 DB "Division -cte/var = -cte anda Long", 0
cadena@@163 DB "Division -cte/var = -cte no anda Long", 0
cadena@@164 DB "Division cte/-cte = -cte anda Long", 0
cadena@@165 DB "Division cte/-cte = -cte no anda Long", 0
cadena@@166 DB "Division var/fn = -cte anda Long", 0
cadena@@167 DB "Division var/fn = -cte no anda Long", 0
cadena@@168 DB "Division fn/var = -cte anda Long", 0
cadena@@169 DB "Division fn/var = -cte no anda Long", 0
cadena@@170 DB "Division -cte/fn = cte anda Long", 0
cadena@@171 DB "Division -cte/fn = cte no anda Long", 0
cadena@@172 DB "Division fn/cte = -cte anda Long", 0
cadena@@173 DB "Division fn/cte = -cte no anda Long", 0
cadena@@174 DB "Division fn/fn = cte anda Long", 0
cadena@@175 DB "Division fn/fn = cte no anda Long", 0
cadena@@176 DB "Division var/fn = var anda Long", 0
cadena@@177 DB "Division var/fn = var no anda Long", 0
cadena@@178 DB "Division -cte/cte = fn anda Long", 0
cadena@@179 DB "Division -cte/cte = fn no anda Long", 0
cadena@@180 DB "Division por cero no anda Long", 0
cadena@@181 DB "Division no anda Long", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida5 proc
MOV ab1@Variable,-5
MOV EAX,ab1@Variable
MOV salida5@Funcion,EAX
RET
salida5 endp
salida10 proc
MOV ab1@Variable,-10
MOV EAX,ab1@Variable
MOV salida10@Funcion,EAX
RET
salida10 endp
start:
MOV a@Variable,10
MOV b@Variable,-5
MOV EAX,a@Variable
CDQ
CMP b@Variable,0
JE _division_cero
MOV EBX,b@Variable
CDQ
IDIV EBX
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV c@Variable,EAX
MOV EAX,c@Variable
MOV EDX,-2
CMP c@Variable,EDX
JNE Label8
invoke MessageBox, NULL, addr cadena@@156, addr cadena@@156, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@157, addr cadena@@157, MB_OK
Label9:
MOV a@Variable,-10
MOV b@Variable,5
MOV EAX,a@Variable
CDQ
CMP b@Variable,0
JE _division_cero
MOV EBX,b@Variable
CDQ
IDIV EBX
MOV var@@aux11,EAX
MOV EAX,var@@aux11
MOV EDX,-2
CMP var@@aux11,EDX
JNE Label16
invoke MessageBox, NULL, addr cadena@@158, addr cadena@@158, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@159, addr cadena@@159, MB_OK
Label17:
MOV a@Variable,10
MOV EAX,a@Variable
CDQ
CMP ECX,0
JE _division_cero
MOV ECX,-5
MOV EBX,-5
CDQ
IDIV EBX
MOV var@@aux18,EAX
MOV EAX,var@@aux18
MOV EDX,-2
CMP var@@aux18,EDX
JNE Label23
invoke MessageBox, NULL, addr cadena@@160, addr cadena@@160, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@161, addr cadena@@161, MB_OK
Label24:
MOV b@Variable,5
MOV EAX,-10
CDQ
CMP b@Variable,0
JE _division_cero
MOV EBX,b@Variable
CDQ
IDIV EBX
MOV var@@aux25,EAX
MOV EAX,var@@aux25
MOV EDX,-2
CMP var@@aux25,EDX
JNE Label30
invoke MessageBox, NULL, addr cadena@@162, addr cadena@@162, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@163, addr cadena@@163, MB_OK
Label31:
MOV EAX,10
CDQ
CMP ECX,0
JE _division_cero
MOV ECX,-5
MOV EBX,-5
CDQ
IDIV EBX
MOV var@@aux31,EAX
MOV EAX,var@@aux31
MOV EDX,-2
CMP var@@aux31,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@164, addr cadena@@164, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@165, addr cadena@@165, MB_OK
Label37:
Label39:
MOV a@Variable,10
CALL salida5
MOV EAX,a@Variable
CDQ
CMP salida5@Funcion,0
JE _division_cero
MOV EBX, salida5@Funcion
MOV EBX,salida5@Funcion
CDQ
IDIV EBX
MOV var@@aux43,EAX
MOV EAX,var@@aux43
MOV EDX,-2
CMP var@@aux43,EDX
JNE Label48
invoke MessageBox, NULL, addr cadena@@166, addr cadena@@166, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@167, addr cadena@@167, MB_OK
Label49:
MOV b@Variable,5
CALL salida10
MOV EAX,salida10@Funcion
CDQ
CMP b@Variable,0
JE _division_cero
MOV EBX,b@Variable
CDQ
IDIV EBX
MOV var@@aux51,EAX
MOV EAX,var@@aux51
MOV EDX,-2
CMP var@@aux51,EDX
JNE Label56
invoke MessageBox, NULL, addr cadena@@168, addr cadena@@168, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@169, addr cadena@@169, MB_OK
Label57:
CALL salida5
MOV EAX,-10
CDQ
CMP salida5@Funcion,0
JE _division_cero
MOV EBX, salida5@Funcion
MOV EBX,salida5@Funcion
CDQ
IDIV EBX
MOV var@@aux58,EAX
MOV EAX,var@@aux58
MOV EDX,2
CMP var@@aux58,EDX
JNE Label63
invoke MessageBox, NULL, addr cadena@@170, addr cadena@@170, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@171, addr cadena@@171, MB_OK
Label64:
CALL salida10
MOV EAX,salida10@Funcion
CDQ
CMP ECX,0
JE _division_cero
MOV ECX,5
MOV EBX,5
CDQ
IDIV EBX
MOV var@@aux65,EAX
MOV EAX,var@@aux65
MOV EDX,-2
CMP var@@aux65,EDX
JNE Label70
invoke MessageBox, NULL, addr cadena@@172, addr cadena@@172, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@173, addr cadena@@173, MB_OK
Label71:
CALL salida10
CALL salida5
MOV EAX,salida10@Funcion
CDQ
CMP salida5@Funcion,0
JE _division_cero
MOV EBX, salida5@Funcion
MOV EBX,salida5@Funcion
CDQ
IDIV EBX
MOV var@@aux73,EAX
MOV EAX,var@@aux73
MOV EDX,2
CMP var@@aux73,EDX
JNE Label78
invoke MessageBox, NULL, addr cadena@@174, addr cadena@@174, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@175, addr cadena@@175, MB_OK
Label79:
MOV a@Variable,10
MOV c@Variable,-2
CALL salida5
MOV EAX,a@Variable
CDQ
CMP salida5@Funcion,0
JE _division_cero
MOV EBX, salida5@Funcion
MOV EBX,salida5@Funcion
CDQ
IDIV EBX
MOV var@@aux82,EAX
MOV EAX,var@@aux82
MOV EAX,c@Variable
CMP var@@aux82,EAX
JNE Label87
invoke MessageBox, NULL, addr cadena@@176, addr cadena@@176, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@177, addr cadena@@177, MB_OK
Label88:
MOV EAX,-10
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
CMP EAX,salida5@Funcion
JNE Label94
invoke MessageBox, NULL, addr cadena@@178, addr cadena@@178, MB_OK
JMP Label95
Label94:
invoke MessageBox, NULL, addr cadena@@179, addr cadena@@179, MB_OK
Label95:
MOV a@Variable,10
MOV b@Variable,0
MOV EAX,a@Variable
CDQ
CMP b@Variable,0
JE _division_cero
MOV EBX,b@Variable
CDQ
IDIV EBX
MOV var@@aux97,EAX
MOV EAX,var@@aux97
MOV c@Variable,EAX
MOV EAX,c@Variable
MOV EDX,2
CMP c@Variable,EDX
JNE Label103
invoke MessageBox, NULL, addr cadena@@180, addr cadena@@180, MB_OK
JMP Label104
Label103:
invoke MessageBox, NULL, addr cadena@@181, addr cadena@@181, MB_OK
Label104:
invoke ExitProcess, 0
end start