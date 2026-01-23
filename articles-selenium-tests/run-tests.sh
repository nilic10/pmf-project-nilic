#!/bin/bash

# Skript za pokretanje Selenium testova sa različitim konfiguracijama

set -e

PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$PROJECT_DIR"

# Boje za ispis
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Funkcija za ispis greške
print_error() {
    echo -e "${RED}❌ Greška: $1${NC}"
    exit 1
}

# Funkcija za ispis uspešnosti
print_success() {
    echo -e "${GREEN}✅ $1${NC}"
}

# Funkcija za ispis informacije
print_info() {
    echo -e "${BLUE}ℹ️  $1${NC}"
}

# Funkcija za ispis opozorenja
print_warning() {
    echo -e "${YELLOW}⚠️  $1${NC}"
}

# Pomoc
show_help() {
    cat << EOF
Korišćenje: ./run-tests.sh [opcija]

Opcije:
    chrome              Pokretanje testova na Chrome-u (lokalno)
    firefox             Pokretanje testova na Firefox-u (lokalno)
    remote-chrome       Pokretanje testova na Chrome-u (Selenium Grid)
    remote-firefox      Pokretanje testova na Firefox-u (Selenium Grid)
    all                 Pokretanje svih testova
    positive            Pokretanje samo pozitivnih testova (Chrome)
    negative            Pokretanje samo negativnih testova (Chrome)
    integration         Pokretanje samo integracijskih testova (Chrome)
    help                Prikazivanje ove poruke

Primeri:
    ./run-tests.sh chrome
    ./run-tests.sh firefox
    ./run-tests.sh positive
    ./run-tests.sh all

EOF
}

# Proverava Maven
check_maven() {
    if ! command -v mvn &> /dev/null; then
        print_error "Maven nije instaliran. Molimo instalirajte Maven pre nego što nastavite."
    fi
}

# Proverava Java
check_java() {
    if ! command -v java &> /dev/null; then
        print_error "Java nije instalirana. Molimo instalirajte Java pre nego što nastavite."
    fi
}

# Pokretanje testova na Chrome-u
run_chrome() {
    print_info "Pokretanje testova na Chrome-u (lokalno)..."
    mvn clean test -Dbrowser=chrome
    print_success "Chrome testovi završeni"
}

# Pokretanje testova na Firefox-u
run_firefox() {
    print_info "Pokretanje testova na Firefox-u (lokalno)..."
    mvn clean test -Dbrowser=firefox
    print_success "Firefox testovi završeni"
}

# Pokretanje testova na Remote Chrome
run_remote_chrome() {
    print_warning "Napomena: Selenium Grid server mora biti pokrenut na http://localhost:4444"
    print_info "Pokretanje testova na Remote Chrome-u..."
    mvn clean test -Dbrowser=remote_chrome -DgridUrl=http://localhost:4444
    print_success "Remote Chrome testovi završeni"
}

# Pokretanje testova na Remote Firefox
run_remote_firefox() {
    print_warning "Napomena: Selenium Grid server mora biti pokrenut na http://localhost:4444"
    print_info "Pokretanje testova na Remote Firefox-u..."
    mvn clean test -Dbrowser=remote_firefox -DgridUrl=http://localhost:4444
    print_success "Remote Firefox testovi završeni"
}

# Pokretanje samo pozitivnih testova
run_positive() {
    print_info "Pokretanje samo pozitivnih testova..."
    mvn clean test -Dbrowser=chrome -Dtest=ArticlesPagePositiveTest
    print_success "Pozitivni testovi završeni"
}

# Pokretanje samo negativnih testova
run_negative() {
    print_info "Pokretanje samo negativnih testova..."
    mvn clean test -Dbrowser=chrome -Dtest=ArticlesPageNegativeTest
    print_success "Negativni testovi završeni"
}

# Pokretanje samo integracijskih testova
run_integration() {
    print_info "Pokretanje samo integracijskih testova..."
    mvn clean test -Dbrowser=chrome -Dtest=ArticlesPageIntegrationTest
    print_success "Integracijski testovi završeni"
}

# Pokretanje svih testova
run_all() {
    print_info "Pokretanje SVIH testova..."
    print_info "1. Chrome testovi..."
    run_chrome
    print_info "2. Firefox testovi..."
    run_firefox
    print_success "Svi testovi završeni!"
}

# Validacija opcije
validate_option() {
    case "$1" in
        chrome|firefox|remote-chrome|remote-firefox|all|positive|negative|integration|help)
            return 0
            ;;
        "")
            return 0
            ;;
        *)
            print_error "Nepoznata opcija: $1"
            ;;
    esac
}

# Glavna logika
main() {
    # Proverava Maven i Java
    check_maven
    check_java

    # Ako nema argumenata, prikazuje help
    if [ $# -eq 0 ]; then
        show_help
        return
    fi

    # Validira opciju
    validate_option "$1"

    # Izvršava odgovarajuću akciju
    case "$1" in
        chrome)
            run_chrome
            ;;
        firefox)
            run_firefox
            ;;
        remote-chrome)
            run_remote_chrome
            ;;
        remote-firefox)
            run_remote_firefox
            ;;
        all)
            run_all
            ;;
        positive)
            run_positive
            ;;
        negative)
            run_negative
            ;;
        integration)
            run_integration
            ;;
        help)
            show_help
            ;;
    esac
}

# Pokreće main funkciju
main "$@"
