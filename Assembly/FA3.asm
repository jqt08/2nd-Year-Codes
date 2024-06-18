section .data
    num1 db 10
    num2 db 12
    num3 db 2
    largest db 0
    newline db 10
    message db "The largest number is: ", 0
    largest_str db "00", 0

section .text
global _start

_start:
    ; Initialize largest with num1
    mov al, [num1]
    mov [largest], al

    ; Compare with num2
    mov al, [num2]
    cmp al, [largest]
    jle check_num3
    mov [largest], al

check_num3:
    ; Compare with num3
    mov al, [num3]
    cmp al, [largest]
    jle done
    mov [largest], al

done:
    ; Convert largest number to string
    mov eax, 0
    mov al, [largest]
    call num_to_ascii

    ; Print the message
    mov eax, 4          ; sys_write
    mov ebx, 1          ; file descriptor (stdout)
    mov ecx, message    ; pointer to message
    mov edx, 22         ; length of message
    int 0x80

    ; Print the largest number string
    mov eax, 4          ; sys_write
    mov ebx, 1          ; file descriptor (stdout)
    lea ecx, [largest_str] ; pointer to largest_str
    mov edx, 2          ; length of largest_str
    int 0x80

    ; Print newline
    mov eax, 4          ; sys_write
    mov ebx, 1          ; file descriptor (stdout)
    lea ecx, [newline]  ; pointer to newline
    mov edx, 1          ; length of newline
    int 0x80

    ; Exit program
    mov eax, 1          ; sys_exit
    xor ebx, ebx        ; status 0
    int 0x80

num_to_ascii:
    ; EAX = number to convert
    ; Result stored in largest_str
    mov ecx, 10         ; divisor for extracting digits
    mov edi, largest_str + 1 ; start from the end of the string

convert_loop:
    xor edx, edx        ; clear edx
    div ecx             ; divide eax by 10, quotient in eax, remainder in edx
    add dl, '0'         ; convert remainder to ASCII
    mov [edi], dl       ; store the character
    dec edi             ; move to the previous character position

    test eax, eax       ; check if quotient is 0
    jnz convert_loop    ; if not, continue converting

    ; Adjust for numbers less than 10
    cmp byte [edi+1], '0'
    jne skip_leading_zero
    mov byte [edi], ' ' ; replace leading zero with space

skip_leading_zero:
    ret
