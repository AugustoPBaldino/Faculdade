#Print

print('Imprime a mensagem e muda de linha')
print('Imprime a mensagem e permance na linha', end='')
print('Continuo na mesma linha!')

#Format
nome = 'Maria'
idade = 30
print('O nome dela é {0} e ela tem {1} anos'.format(nome,idade))

#F-String (Mais poderosa)
nome = 'Fabio'
peso = 70
msg = f'Olá, meu nome é {nome} e eu peso {peso} quilos.'
print(msg)
