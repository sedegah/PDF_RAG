# PDF RAG System (Java)

A Java tool that performs RAG (Red/Amber/Green) analysis on PDF documents. It:

- Extracts text from PDFs using **Apache PDFBox**
- Assigns RAG statuses based on keyword rules
- Highlights or annotates PDFs with colored overlays
- Generates a summary report PDF
- Offers both a CLI and a Swing-based GUI

## Prerequisites

- Java 11 or higher
- Maven 3.x

## Installation & Build

```bash
git clone https://github.com/your-username/pdf-rag-java.git
cd pdf-rag-java
mvn package
