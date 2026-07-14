# 📊 IMCApp - Calculadora de Índice de Massa Corporal

## 📝 Descrição

**IMCApp** é um aplicativo Android moderno desenvolvido em **Kotlin** utilizando **Jetpack Compose** para calcular o Índice de Massa Corporal (IMC) de forma rápida e intuitiva. O app oferece uma interface visual limpa e responsiva, com feedback visual imediato através de cores que indicam a categoria de peso do usuário.

---

## 🎯 Finalidade

O objetivo principal do IMCApp é:

- ✅ Facilitar o cálculo do Índice de Massa Corporal (IMC)
- ✅ Fornecer classificação visual imediata do resultado (cores específicas por categoria)
- ✅ Oferecer uma experiência de usuário intuitiva e acessível
- ✅ Servir como ferramenta educativa para monitoramento de saúde

O cálculo do IMC é baseado na fórmula internacional:
```
IMC = Peso (kg) / Altura (m)²
```

---

## 🏗️ Arquitetura do Projeto

### Estrutura de Diretórios

```
IMCApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/br/com/fiap/imcapp/
│   │   │   │   └── Main.kt           # Componente principal da aplicação
│   │   │   ├── res/                  # Recursos (imagens, strings, cores)
│   │   │   └── AndroidManifest.xml   # Configuração da aplicação
│   │   ├── test/                     # Testes unitários
│   │   └── androidTest/              # Testes instrumentais
│   └── build.gradle.kts              # Configuração de build
├── build.gradle.kts                  # Build root
├── settings.gradle.kts               # Configurações Gradle
└── imcApp.apk                        # APK compilado
```

### Stack Tecnológico

| Tecnologia | Versão | Propósito |
|------------|--------|----------|
| **Kotlin** | 1.9.x | Linguagem de programação |
| **Android SDK** | API 36 | Framework Android |
| **Jetpack Compose** | 1.5.x | UI declarativa moderna |
| **Material Design 3** | Latest | Sistema de design |
| **Gradle** | 8.x | Build automation |

---

## 🎨 Análise de Componentes

### 1. **MainActivity.kt** - Ponto de Entrada

A classe `MainActivity` configura a interface da aplicação:
- Estende `ComponentActivity` para suporte a Compose
- Controla a barra de status com ícones claros
- Define o tema Material 3

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
        setContent { 
            MaterialTheme { IMCScreen() }
        }
    }
}
```

### 2. **Função Calcular()** - Lógica de Cálculo

```kotlin
fun Calcular(peso: String, altura: String): Double {
    var pesoConvertido: Double = peso.toDouble()
    var alturaConvertida: Double = altura.toDouble()
    var resultadoImc = (pesoConvertido / (alturaConvertida * alturaConvertida))
    return resultadoImc
}
```

**Observação:** A altura é recebida em centímetros e convertida para metros na fórmula.

### 3. **Composable IMCScreen()** - Interface Principal

Composto por 3 seções principais:

#### **Seção 1: Header**
- Logo visual do IMC
- Título "CALCULADORA IMC"
- Fundo com cor corporativa FIAP (vermelho)

#### **Seção 2: Formulário de Entrada**
- Campo **Peso (kg)**: Aceita apenas dígitos e ponto decimal
- Campo **Altura**: Aceita valores em centímetros
- Botão **CALCULAR**: Valida os campos e executa o cálculo
- Botão **LIMPAR**: Reseta todos os campos

**Filtros de Entrada:**
```kotlin
val filtro = it.filter { it.isDigit() || it == '.' || it == ',' }
peso = filtro.replace(',', '.')
```

#### **Seção 3: Card de Resultado**
Exibe categorias com cores específicas:

| Categoria | Intervalo IMC | Cor | Código |
|-----------|--------------|-----|--------|
| **Magreza** | < 18.5 | 🔴 Vermelho | `#FF0000` |
| **Peso Ideal** | 18.5 - 24.9 | 🟢 Verde | `#74FF00` |
| **Sobrepeso** | 25.0 - 29.9 | 🟡 Amarelo | `#FFC900` |
| **Obesidade** | 30.0 - 39.9 | 🟠 Laranja | `#FF6F00` |
| **Obesidade Grave** | ≥ 40 | 🔴 Vermelho escuro | `#FF0800` |

---

## 📱 Configuração da Aplicação

### Build Configuration (`build.gradle.kts`)

```gradle
android {
    namespace = "br.com.fiap.imcapp"
    compileSdk = 36              // Android 15
    
    defaultConfig {
        applicationId = "br.com.fiap.imcapp"
        minSdk = 27               // Android 8.1+
        targetSdk = 36            // Android 15
        versionCode = 1
        versionName = "1.0"
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
```

### Dependências

- `androidx.core.ktx` - Extensões Kotlin para Android
- `androidx.appcompat` - Compatibilidade com versões antigas
- `androidx.material3` - Material Design 3
- `androidx.foundation` - Fundações Compose
- `androidx.activity.compose` - Integração Compose com Activity

---

## 🚀 Como Testar

### Opção 1: Usar o APK Compilado

1. Baixe o arquivo `imcApp.apk` do repositório
2. Instale em um dispositivo ou emulador Android (mínimo API 27)
   ```bash
   adb install imcApp.apk
   ```
3. Abra o app a partir do menu de aplicativos

### Opção 2: Compilar Localmente

#### Pré-requisitos
- Android Studio (última versão)
- Java 11 ou superior
- Android SDK 36+ instalado
- Git

#### Passos

1. **Clone o repositório**
   ```bash
   git clone https://github.com/gustavosampa1o/IMCApp.git
   cd IMCApp
   ```

2. **Abra no Android Studio**
   - Abra Android Studio
   - Selecione "Open an Existing Project"
   - Navegue até a pasta IMCApp
   - Clique em "Open"

3. **Sincronize as dependências Gradle**
   ```bash
   ./gradlew syncDebug
   ```

4. **Compile o projeto**
   ```bash
   ./gradlew build
   ```

5. **Execute no emulador ou dispositivo**
   ```bash
   ./gradlew installDebug
   ```
   Ou pressione `Shift + F10` no Android Studio

### Opção 3: Compilar via Linha de Comando

```bash
# Build debug
./gradlew assembleDebug

# Build release
./gradlew assembleRelease

# Instalar em dispositivo conectado
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

---

## 🧪 Testando a Funcionalidade

### Casos de Teste Sugeridos

| Entrada | Peso | Altura | Resultado Esperado | Categoria |
|---------|------|--------|-------------------|-----------|
| Teste 1 | 70 kg | 175 cm | 22.86 | ✅ Peso Ideal |
| Teste 2 | 50 kg | 170 cm | 17.30 | ⚠️ Magreza |
| Teste 3 | 85 kg | 170 cm | 29.41 | ⚠️ Sobrepeso |
| Teste 4 | 100 kg | 170 cm | 34.60 | ⚠️ Obesidade |
| Teste 5 | 120 kg | 170 cm | 41.51 | ⛔ Obesidade Grave |

### Fluxo de Teste Manual

1. ✅ Abra o aplicativo
2. ✅ Tente inserir apenas números (teste: "abc" → não deve aceitar)
3. ✅ Tente usar vírgula como separador decimal (teste: "70,5" → deve converter para ponto)
4. ✅ Teste campo vazio + botão calcular (deve não fazer nada)
5. ✅ Teste o botão "LIMPAR" (deve resetar campos)
6. ✅ Verifique as cores de resultado para cada categoria

---

## 🎨 Design e UX

### Paleta de Cores

```
Cores Corporativas (FIAP):
- Vermelho Primário: #D32F2F (R.color.vermelho_fiap)
- Cor do Texto: Branco/Cinza (R.color.cor_do_texto)
```

### Componentes Visuais

- **Cards**: Bordas arredondadas com sombras
- **Botões**: Toques de feedback visual
- **TextField**: Bordas customizadas com foco colorido
- **Espaçamento**: Padding consistente de 16-32 dp

---

## 📊 Análise de Qualidade

### Pontos Fortes ✅

1. **UI Moderna**: Utiliza Jetpack Compose (framework mais moderno do Android)
2. **Design Responsivo**: Adapta-se a diferentes tamanhos de tela
3. **Validação de Entrada**: Filtra caracteres inválidos
4. **Feedback Visual**: Cores indicam categorias de IMC
5. **Experiência de Usuário**: Interface intuitiva e limpa
6. **Compatibilidade**: Suporta Android 8.1+ (API 27)

### Pontos de Melhoria 🔧

1. **Tratamento de Erros**: Poderia validar melhor entradas vazias
2. **Testes Automatizados**: Não há testes implementados
3. **Internacionalização**: Strings hardcoded, sem suporte a múltiplos idiomas
4. **Persistência**: Não salva histórico de cálculos
5. **Acessibilidade**: Faltam labels descritivos para screen readers
6. **Documentação de Código**: Comentários limitados

---

## 📋 Requisitos do Sistema

- **SO Mínimo**: Android 8.1 (API 27)
- **SO Alvo**: Android 15 (API 36)
- **RAM Recomendado**: 2GB+
- **Espaço em Disco**: ~50MB
- **Permissões**: Nenhuma permissão necessária

---

## 🤝 Contribuindo

Sugestões de melhorias são bem-vindas! Você pode:

1. Fazer um fork do repositório
2. Criar uma branch para sua feature (`git checkout -b feature/MeuImprovement`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/MeuImprovement`)
5. Abrir um Pull Request

---

## 📝 Licença

Este projeto está disponível sob licença aberta. Consulte o repositório para mais detalhes.

---

## 👤 Autor

**Gustavo Sampa**  
GitHub: [@gustavosampa1o](https://github.com/gustavosampa1o)

---

## 🔗 Links Úteis

- [Android Developer Documentation](https://developer.android.com)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Material Design 3](https://m3.material.io)
- [Calcular IMC - OMS](https://www.who.int)

---

**Última atualização**: Julho 2025  
**Versão da Aplicação**: 1.0
